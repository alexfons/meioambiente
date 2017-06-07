(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoobraAmbientalController', TipoobraAmbientalController);

    TipoobraAmbientalController.$inject = ['Tipoobra'];

    function TipoobraAmbientalController(Tipoobra) {

        var vm = this;

        vm.tipoobras = [];

        loadAll();

        function loadAll() {
            Tipoobra.query(function(result) {
                vm.tipoobras = result;
                vm.searchQuery = null;
            });
        }
    }
})();
