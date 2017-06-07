(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ListamovimentacaoAmbientalController', ListamovimentacaoAmbientalController);

    ListamovimentacaoAmbientalController.$inject = ['Listamovimentacao'];

    function ListamovimentacaoAmbientalController(Listamovimentacao) {

        var vm = this;

        vm.listamovimentacaos = [];

        loadAll();

        function loadAll() {
            Listamovimentacao.query(function(result) {
                vm.listamovimentacaos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
