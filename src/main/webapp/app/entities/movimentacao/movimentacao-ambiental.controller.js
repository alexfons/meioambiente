(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('MovimentacaoAmbientalController', MovimentacaoAmbientalController);

    MovimentacaoAmbientalController.$inject = ['Movimentacao'];

    function MovimentacaoAmbientalController(Movimentacao) {

        var vm = this;

        vm.movimentacaos = [];

        loadAll();

        function loadAll() {
            Movimentacao.query(function(result) {
                vm.movimentacaos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
