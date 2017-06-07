(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrencianotificacaoAmbientalDeleteController',OcorrencianotificacaoAmbientalDeleteController);

    OcorrencianotificacaoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Ocorrencianotificacao'];

    function OcorrencianotificacaoAmbientalDeleteController($uibModalInstance, entity, Ocorrencianotificacao) {
        var vm = this;

        vm.ocorrencianotificacao = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Ocorrencianotificacao.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
