(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FaturaAmbientalController', FaturaAmbientalController);

    FaturaAmbientalController.$inject = ['Fatura'];

    function FaturaAmbientalController(Fatura) {

        var vm = this;

        vm.faturas = [];

        loadAll();

        function loadAll() {
            Fatura.query(function(result) {
                vm.faturas = result;
                vm.searchQuery = null;
            });
        }
    }
})();
