(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AtividadelicencaAmbientalController', AtividadelicencaAmbientalController);

    AtividadelicencaAmbientalController.$inject = ['Atividadelicenca'];

    function AtividadelicencaAmbientalController(Atividadelicenca) {

        var vm = this;

        vm.atividadelicencas = [];

        loadAll();

        function loadAll() {
            Atividadelicenca.query(function(result) {
                vm.atividadelicencas = result;
                vm.searchQuery = null;
            });
        }
    }
})();
