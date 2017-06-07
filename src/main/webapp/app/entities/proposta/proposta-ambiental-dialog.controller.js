(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('PropostaAmbientalDialogController', PropostaAmbientalDialogController);

    PropostaAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Proposta'];

    function PropostaAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Proposta) {
        var vm = this;

        vm.proposta = entity;
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
            if (vm.proposta.id !== null) {
                Proposta.update(vm.proposta, onSaveSuccess, onSaveError);
            } else {
                Proposta.save(vm.proposta, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:propostaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
