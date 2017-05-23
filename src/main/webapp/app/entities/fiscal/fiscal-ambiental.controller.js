(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FiscalAmbientalController', FiscalAmbientalController);

    FiscalAmbientalController.$inject = ['Fiscal'];

    function FiscalAmbientalController(Fiscal) {

        var vm = this;

        vm.fiscals = [];

        loadAll();

        function loadAll() {
            Fiscal.query(function(result) {
                vm.fiscals = result;
                vm.searchQuery = null;
            });
        }
    }
})();
