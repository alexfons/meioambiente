(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AtividadeexecutadamensalAmbientalController', AtividadeexecutadamensalAmbientalController);

    AtividadeexecutadamensalAmbientalController.$inject = ['Atividadeexecutadamensal'];

    function AtividadeexecutadamensalAmbientalController(Atividadeexecutadamensal) {

        var vm = this;

        vm.atividadeexecutadamensals = [];

        loadAll();

        function loadAll() {
            Atividadeexecutadamensal.query(function(result) {
                vm.atividadeexecutadamensals = result;
                vm.searchQuery = null;
            });
        }
    }
})();
