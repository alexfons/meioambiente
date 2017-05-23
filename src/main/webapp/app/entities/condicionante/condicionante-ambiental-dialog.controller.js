(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CondicionanteAmbientalDialogController', CondicionanteAmbientalDialogController);

    CondicionanteAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Condicionante'];

    function CondicionanteAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Condicionante) {
        var vm = this;

        vm.condicionante = entity;
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
            if (vm.condicionante.id !== null) {
                Condicionante.update(vm.condicionante, onSaveSuccess, onSaveError);
            } else {
                Condicionante.save(vm.condicionante, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:condicionanteUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
