(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ColunaAmbientalController', ColunaAmbientalController);

    ColunaAmbientalController.$inject = ['Coluna'];

    function ColunaAmbientalController(Coluna) {

        var vm = this;

        vm.colunas = [];

        loadAll();

        function loadAll() {
            Coluna.query(function(result) {
                vm.colunas = result;
                vm.searchQuery = null;
            });
        }
    }
})();
