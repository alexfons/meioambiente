(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('BalancoAmbientalController', BalancoAmbientalController);

    BalancoAmbientalController.$inject = ['Balanco'];

    function BalancoAmbientalController(Balanco) {

        var vm = this;

        vm.balancos = [];

        loadAll();

        function loadAll() {
            Balanco.query(function(result) {
                vm.balancos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
