(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('PlanocontasAmbientalController', PlanocontasAmbientalController);

    PlanocontasAmbientalController.$inject = ['Planocontas'];

    function PlanocontasAmbientalController(Planocontas) {

        var vm = this;

        vm.planocontas = [];

        loadAll();

        function loadAll() {
            Planocontas.query(function(result) {
                vm.planocontas = result;
                vm.searchQuery = null;
            });
        }
    }
})();
