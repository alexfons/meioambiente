(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('BancoAmbientalController', BancoAmbientalController);

    BancoAmbientalController.$inject = ['Banco'];

    function BancoAmbientalController(Banco) {

        var vm = this;

        vm.bancos = [];

        loadAll();

        function loadAll() {
            Banco.query(function(result) {
                vm.bancos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
