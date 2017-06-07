(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ContratoAmbientalController', ContratoAmbientalController);

    ContratoAmbientalController.$inject = ['Contrato'];

    function ContratoAmbientalController(Contrato) {

        var vm = this;

        vm.contratoes = [];

        loadAll();

        function loadAll() {
            Contrato.query(function(result) {
                vm.contratoes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
