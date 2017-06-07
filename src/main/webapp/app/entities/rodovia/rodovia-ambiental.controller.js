(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('RodoviaAmbientalController', RodoviaAmbientalController);

    RodoviaAmbientalController.$inject = ['Rodovia'];

    function RodoviaAmbientalController(Rodovia) {

        var vm = this;

        vm.rodovias = [];

        loadAll();

        function loadAll() {
            Rodovia.query(function(result) {
                vm.rodovias = result;
                vm.searchQuery = null;
            });
        }
    }
})();
