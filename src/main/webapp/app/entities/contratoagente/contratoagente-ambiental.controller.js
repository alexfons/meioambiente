(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ContratoagenteAmbientalController', ContratoagenteAmbientalController);

    ContratoagenteAmbientalController.$inject = ['Contratoagente'];

    function ContratoagenteAmbientalController(Contratoagente) {

        var vm = this;

        vm.contratoagentes = [];

        loadAll();

        function loadAll() {
            Contratoagente.query(function(result) {
                vm.contratoagentes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
