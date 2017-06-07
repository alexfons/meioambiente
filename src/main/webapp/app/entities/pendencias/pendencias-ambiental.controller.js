(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('PendenciasAmbientalController', PendenciasAmbientalController);

    PendenciasAmbientalController.$inject = ['Pendencias'];

    function PendenciasAmbientalController(Pendencias) {

        var vm = this;

        vm.pendencias = [];

        loadAll();

        function loadAll() {
            Pendencias.query(function(result) {
                vm.pendencias = result;
                vm.searchQuery = null;
            });
        }
    }
})();
