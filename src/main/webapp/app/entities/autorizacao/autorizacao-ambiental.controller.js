(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AutorizacaoAmbientalController', AutorizacaoAmbientalController);

    AutorizacaoAmbientalController.$inject = ['Autorizacao'];

    function AutorizacaoAmbientalController(Autorizacao) {

        var vm = this;

        vm.autorizacaos = [];

        loadAll();

        function loadAll() {
            Autorizacao.query(function(result) {
                vm.autorizacaos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
