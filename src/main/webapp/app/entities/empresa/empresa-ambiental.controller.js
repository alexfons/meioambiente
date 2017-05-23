(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('EmpresaAmbientalController', EmpresaAmbientalController);

    EmpresaAmbientalController.$inject = ['Empresa'];

    function EmpresaAmbientalController(Empresa) {

        var vm = this;

        vm.empresas = [];

        loadAll();

        function loadAll() {
            Empresa.query(function(result) {
                vm.empresas = result;
                vm.searchQuery = null;
            });
        }
    }
})();
