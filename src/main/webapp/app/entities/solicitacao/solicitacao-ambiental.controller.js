(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('SolicitacaoAmbientalController', SolicitacaoAmbientalController);

    SolicitacaoAmbientalController.$inject = ['Solicitacao'];

    function SolicitacaoAmbientalController(Solicitacao) {

        var vm = this;

        vm.solicitacaos = [];

        loadAll();

        function loadAll() {
            Solicitacao.query(function(result) {
                vm.solicitacaos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
