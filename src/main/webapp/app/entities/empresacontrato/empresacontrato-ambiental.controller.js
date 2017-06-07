(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('EmpresacontratoAmbientalController', EmpresacontratoAmbientalController);

    EmpresacontratoAmbientalController.$inject = ['Empresacontrato'];

    function EmpresacontratoAmbientalController(Empresacontrato) {

        var vm = this;

        vm.empresacontratoes = [];

        loadAll();

        function loadAll() {
            Empresacontrato.query(function(result) {
                vm.empresacontratoes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
