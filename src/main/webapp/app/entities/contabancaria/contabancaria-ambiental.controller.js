(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ContabancariaAmbientalController', ContabancariaAmbientalController);

    ContabancariaAmbientalController.$inject = ['Contabancaria'];

    function ContabancariaAmbientalController(Contabancaria) {

        var vm = this;

        vm.contabancarias = [];

        loadAll();

        function loadAll() {
            Contabancaria.query(function(result) {
                vm.contabancarias = result;
                vm.searchQuery = null;
            });
        }
    }
})();
