(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('QuantitativoAmbientalController', QuantitativoAmbientalController);

    QuantitativoAmbientalController.$inject = ['Quantitativo'];

    function QuantitativoAmbientalController(Quantitativo) {

        var vm = this;

        vm.quantitativos = [];

        loadAll();

        function loadAll() {
            Quantitativo.query(function(result) {
                vm.quantitativos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
