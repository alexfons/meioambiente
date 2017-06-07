(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciaapresentacaoAmbientalDialogController', OcorrenciaapresentacaoAmbientalDialogController);

    OcorrenciaapresentacaoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Ocorrenciaapresentacao', 'Ocorrencia'];

    function OcorrenciaapresentacaoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Ocorrenciaapresentacao, Ocorrencia) {
        var vm = this;

        vm.ocorrenciaapresentacao = entity;
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
            if (vm.ocorrenciaapresentacao.id !== null) {
                Ocorrenciaapresentacao.update(vm.ocorrenciaapresentacao, onSaveSuccess, onSaveError);
            } else {
                Ocorrenciaapresentacao.save(vm.ocorrenciaapresentacao, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:ocorrenciaapresentacaoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
