(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('PropostaAmbientalController', PropostaAmbientalController);

    PropostaAmbientalController.$inject = ['Proposta'];

    function PropostaAmbientalController(Proposta) {

        var vm = this;

        vm.propostas = [];

        loadAll();

        function loadAll() {
            Proposta.query(function(result) {
                vm.propostas = result;
                vm.searchQuery = null;
            });
        }
    }
})();
