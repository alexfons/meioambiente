(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('SupreAmbientalController', SupreAmbientalController);

    SupreAmbientalController.$inject = ['Supre'];

    function SupreAmbientalController(Supre) {

        var vm = this;

        vm.supres = [];

        loadAll();

        function loadAll() {
            Supre.query(function(result) {
                vm.supres = result;
                vm.searchQuery = null;
            });
        }
    }
})();
