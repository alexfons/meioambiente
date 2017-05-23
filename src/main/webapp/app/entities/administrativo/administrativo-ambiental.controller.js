(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AdministrativoAmbientalController', AdministrativoAmbientalController);

    AdministrativoAmbientalController.$inject = ['Administrativo'];

    function AdministrativoAmbientalController(Administrativo) {

        var vm = this;

        vm.administrativos = [];

        loadAll();

        function loadAll() {
            Administrativo.query(function(result) {
                vm.administrativos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
