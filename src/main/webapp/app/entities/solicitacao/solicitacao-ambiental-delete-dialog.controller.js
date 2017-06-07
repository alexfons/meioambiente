(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('SolicitacaoAmbientalDeleteController',SolicitacaoAmbientalDeleteController);

    SolicitacaoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Solicitacao'];

    function SolicitacaoAmbientalDeleteController($uibModalInstance, entity, Solicitacao) {
        var vm = this;

        vm.solicitacao = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Solicitacao.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
