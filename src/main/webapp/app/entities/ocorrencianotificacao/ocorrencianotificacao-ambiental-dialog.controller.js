(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrencianotificacaoAmbientalDialogController', OcorrencianotificacaoAmbientalDialogController);

    OcorrencianotificacaoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Ocorrencianotificacao', 'Ocorrencia'];

    function OcorrencianotificacaoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Ocorrencianotificacao, Ocorrencia) {
        var vm = this;

        vm.ocorrencianotificacao = entity;
        vm.clear = clear;
        vm.save = save;
        vm.ocorrencias = Ocorrencia.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.ocorrencianotificacao.id !== null) {
                Ocorrencianotificacao.update(vm.ocorrencianotificacao, onSaveSuccess, onSaveError);
            } else {
                Ocorrencianotificacao.save(vm.ocorrencianotificacao, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:ocorrencianotificacaoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
