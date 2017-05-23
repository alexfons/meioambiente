(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AtividadelicencaAmbientalDialogController', AtividadelicencaAmbientalDialogController);

    AtividadelicencaAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Atividadelicenca'];

    function AtividadelicencaAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Atividadelicenca) {
        var vm = this;

        vm.atividadelicenca = entity;
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
            if (vm.atividadelicenca.id !== null) {
                Atividadelicenca.update(vm.atividadelicenca, onSaveSuccess, onSaveError);
            } else {
                Atividadelicenca.save(vm.atividadelicenca, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:atividadelicencaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
