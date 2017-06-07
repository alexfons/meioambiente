(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('PropostaAmbientalDialogController', PropostaAmbientalDialogController);

    PropostaAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Proposta', 'Empresa', 'Editallote'];

    function PropostaAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Proposta, Empresa, Editallote) {
        var vm = this;

        vm.proposta = entity;
        vm.clear = clear;
        vm.save = save;
        vm.empresas = Empresa.query();
        vm.editallotes = Editallote.query();

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
