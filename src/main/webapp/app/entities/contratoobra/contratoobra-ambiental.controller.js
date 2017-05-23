(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ContratoobraAmbientalController', ContratoobraAmbientalController);

    ContratoobraAmbientalController.$inject = ['Contratoobra'];

    function ContratoobraAmbientalController(Contratoobra) {

        var vm = this;

        vm.contratoobras = [];

        loadAll();

        function loadAll() {
            Contratoobra.query(function(result) {
                vm.contratoobras = result;
                vm.searchQuery = null;
            });
        }
    }
})();
