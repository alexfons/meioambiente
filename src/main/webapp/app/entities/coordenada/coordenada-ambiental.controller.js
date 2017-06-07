(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CoordenadaAmbientalController', CoordenadaAmbientalController);

    CoordenadaAmbientalController.$inject = ['Coordenada'];

    function CoordenadaAmbientalController(Coordenada) {

        var vm = this;

        vm.coordenadas = [];

        loadAll();

        function loadAll() {
            Coordenada.query(function(result) {
                vm.coordenadas = result;
                vm.searchQuery = null;
            });
        }
    }
})();
