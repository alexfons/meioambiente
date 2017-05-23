(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ProjetoAmbientalController', ProjetoAmbientalController);

    ProjetoAmbientalController.$inject = ['Projeto'];

    function ProjetoAmbientalController(Projeto) {

        var vm = this;

        vm.projetos = [];

        loadAll();

        function loadAll() {
            Projeto.query(function(result) {
                vm.projetos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
