(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('PlanocontasAmbientalDialogController', PlanocontasAmbientalDialogController);

    PlanocontasAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Planocontas'];

    function PlanocontasAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Planocontas) {
        var vm = this;

        vm.planocontas = entity;
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
            if (vm.planocontas.id !== null) {
                Planocontas.update(vm.planocontas, onSaveSuccess, onSaveError);
            } else {
                Planocontas.save(vm.planocontas, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:planocontasUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
