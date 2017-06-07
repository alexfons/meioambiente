(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FuncionarioAmbientalDialogController', FuncionarioAmbientalDialogController);

    FuncionarioAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Funcionario'];

    function FuncionarioAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Funcionario) {
        var vm = this;

        vm.funcionario = entity;
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
            if (vm.funcionario.id !== null) {
                Funcionario.update(vm.funcionario, onSaveSuccess, onSaveError);
            } else {
                Funcionario.save(vm.funcionario, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:funcionarioUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
