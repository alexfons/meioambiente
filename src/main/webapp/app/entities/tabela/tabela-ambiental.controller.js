(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TabelaAmbientalController', TabelaAmbientalController);

    TabelaAmbientalController.$inject = ['Tabela'];

    function TabelaAmbientalController(Tabela) {

        var vm = this;

        vm.tabelas = [];

        loadAll();

        function loadAll() {
            Tabela.query(function(result) {
                vm.tabelas = result;
                vm.searchQuery = null;
            });
        }
    }
})();
