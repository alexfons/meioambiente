(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('HistoricoAmbientalController', HistoricoAmbientalController);

    HistoricoAmbientalController.$inject = ['Historico'];

    function HistoricoAmbientalController(Historico) {

        var vm = this;

        vm.historicos = [];

        loadAll();

        function loadAll() {
            Historico.query(function(result) {
                vm.historicos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
