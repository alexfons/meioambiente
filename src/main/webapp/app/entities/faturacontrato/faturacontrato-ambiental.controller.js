(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FaturacontratoAmbientalController', FaturacontratoAmbientalController);

    FaturacontratoAmbientalController.$inject = ['Faturacontrato'];

    function FaturacontratoAmbientalController(Faturacontrato) {

        var vm = this;

        vm.faturacontratoes = [];

        loadAll();

        function loadAll() {
            Faturacontrato.query(function(result) {
                vm.faturacontratoes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
