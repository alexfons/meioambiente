(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('NaturezaAmbientalController', NaturezaAmbientalController);

    NaturezaAmbientalController.$inject = ['Natureza'];

    function NaturezaAmbientalController(Natureza) {

        var vm = this;

        vm.naturezas = [];

        loadAll();

        function loadAll() {
            Natureza.query(function(result) {
                vm.naturezas = result;
                vm.searchQuery = null;
            });
        }
    }
})();
