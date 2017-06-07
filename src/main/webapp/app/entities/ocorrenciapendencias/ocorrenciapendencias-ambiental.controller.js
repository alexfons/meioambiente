(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciapendenciasAmbientalController', OcorrenciapendenciasAmbientalController);

    OcorrenciapendenciasAmbientalController.$inject = ['Ocorrenciapendencias'];

    function OcorrenciapendenciasAmbientalController(Ocorrenciapendencias) {

        var vm = this;

        vm.ocorrenciapendencias = [];

        loadAll();

        function loadAll() {
            Ocorrenciapendencias.query(function(result) {
                vm.ocorrenciapendencias = result;
                vm.searchQuery = null;
            });
        }
    }
})();
