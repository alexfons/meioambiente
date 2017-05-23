(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciaapresentacaoAmbientalDeleteController',OcorrenciaapresentacaoAmbientalDeleteController);

    OcorrenciaapresentacaoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Ocorrenciaapresentacao'];

    function OcorrenciaapresentacaoAmbientalDeleteController($uibModalInstance, entity, Ocorrenciaapresentacao) {
        var vm = this;

        vm.ocorrenciaapresentacao = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Ocorrenciaapresentacao.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
