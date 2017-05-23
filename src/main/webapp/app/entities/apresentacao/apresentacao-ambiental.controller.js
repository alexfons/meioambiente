(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ApresentacaoAmbientalController', ApresentacaoAmbientalController);

    ApresentacaoAmbientalController.$inject = ['Apresentacao'];

    function ApresentacaoAmbientalController(Apresentacao) {

        var vm = this;

        vm.apresentacaos = [];

        loadAll();

        function loadAll() {
            Apresentacao.query(function(result) {
                vm.apresentacaos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
