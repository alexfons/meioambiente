(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('LicencaAmbientalController', LicencaAmbientalController);

    LicencaAmbientalController.$inject = ['Licenca'];

    function LicencaAmbientalController(Licenca) {

        var vm = this;

        vm.licencas = [];

        loadAll();

        function loadAll() {
            Licenca.query(function(result) {
                vm.licencas = result;
                vm.searchQuery = null;
            });
        }
    }
})();
