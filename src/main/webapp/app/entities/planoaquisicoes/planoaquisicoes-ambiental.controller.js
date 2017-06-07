(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('PlanoaquisicoesAmbientalController', PlanoaquisicoesAmbientalController);

    PlanoaquisicoesAmbientalController.$inject = ['Planoaquisicoes'];

    function PlanoaquisicoesAmbientalController(Planoaquisicoes) {

        var vm = this;

        vm.planoaquisicoes = [];

        loadAll();

        function loadAll() {
            Planoaquisicoes.query(function(result) {
                vm.planoaquisicoes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
