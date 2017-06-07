(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CategoriainversaoAmbientalController', CategoriainversaoAmbientalController);

    CategoriainversaoAmbientalController.$inject = ['Categoriainversao'];

    function CategoriainversaoAmbientalController(Categoriainversao) {

        var vm = this;

        vm.categoriainversaos = [];

        loadAll();

        function loadAll() {
            Categoriainversao.query(function(result) {
                vm.categoriainversaos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
