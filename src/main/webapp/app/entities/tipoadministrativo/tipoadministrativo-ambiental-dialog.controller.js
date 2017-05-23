(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoadministrativoAmbientalDialogController', TipoadministrativoAmbientalDialogController);

    TipoadministrativoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Tipoadministrativo'];

    function TipoadministrativoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Tipoadministrativo) {
        var vm = this;

        vm.tipoadministrativo = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.tipoadministrativo.id !== null) {
                Tipoadministrativo.update(vm.tipoadministrativo, onSaveSuccess, onSaveError);
            } else {
                Tipoadministrativo.save(vm.tipoadministrativo, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:tipoadministrativoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
