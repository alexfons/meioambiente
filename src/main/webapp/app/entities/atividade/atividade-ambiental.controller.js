(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AtividadeAmbientalController', AtividadeAmbientalController);

    AtividadeAmbientalController.$inject = ['Atividade'];

    function AtividadeAmbientalController(Atividade) {

        var vm = this;

        vm.atividades = [];

        loadAll();

        function loadAll() {
            Atividade.query(function(result) {
                vm.atividades = result;
                vm.searchQuery = null;
            });
        }
    }
})();
