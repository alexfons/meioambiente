(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CondicionanteAmbientalController', CondicionanteAmbientalController);

    CondicionanteAmbientalController.$inject = ['Condicionante'];

    function CondicionanteAmbientalController(Condicionante) {

        var vm = this;

        vm.condicionantes = [];

        loadAll();

        function loadAll() {
            Condicionante.query(function(result) {
                vm.condicionantes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
