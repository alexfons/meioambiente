(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ObrafisicomensalAmbientalController', ObrafisicomensalAmbientalController);

    ObrafisicomensalAmbientalController.$inject = ['Obrafisicomensal'];

    function ObrafisicomensalAmbientalController(Obrafisicomensal) {

        var vm = this;

        vm.obrafisicomensals = [];

        loadAll();

        function loadAll() {
            Obrafisicomensal.query(function(result) {
                vm.obrafisicomensals = result;
                vm.searchQuery = null;
            });
        }
    }
})();
