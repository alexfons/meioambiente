(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('QuantitativoAmbientalDialogController', QuantitativoAmbientalDialogController);

    QuantitativoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Quantitativo', 'Medicao'];

    function QuantitativoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Quantitativo, Medicao) {
        var vm = this;

        vm.quantitativo = entity;
        vm.clear = clear;
        vm.save = save;
        vm.medicaos = Medicao.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.quantitativo.id !== null) {
                Quantitativo.update(vm.quantitativo, onSaveSuccess, onSaveError);
            } else {
                Quantitativo.save(vm.quantitativo, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:quantitativoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
