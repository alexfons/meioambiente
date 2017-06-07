(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('NaturezaAmbientalDialogController', NaturezaAmbientalDialogController);

    NaturezaAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Natureza'];

    function NaturezaAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Natureza) {
        var vm = this;

        vm.natureza = entity;
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
            if (vm.natureza.id !== null) {
                Natureza.update(vm.natureza, onSaveSuccess, onSaveError);
            } else {
                Natureza.save(vm.natureza, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:naturezaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
