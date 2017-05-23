(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AditivocontratoAmbientalController', AditivocontratoAmbientalController);

    AditivocontratoAmbientalController.$inject = ['Aditivocontrato'];

    function AditivocontratoAmbientalController(Aditivocontrato) {

        var vm = this;

        vm.aditivocontratoes = [];

        loadAll();

        function loadAll() {
            Aditivocontrato.query(function(result) {
                vm.aditivocontratoes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
