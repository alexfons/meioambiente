(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('RegistroAmbientalController', RegistroAmbientalController);

    RegistroAmbientalController.$inject = ['Registro'];

    function RegistroAmbientalController(Registro) {

        var vm = this;

        vm.registros = [];

        loadAll();

        function loadAll() {
            Registro.query(function(result) {
                vm.registros = result;
                vm.searchQuery = null;
            });
        }
    }
})();
