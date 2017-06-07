(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('PagfuncionarioAmbientalController', PagfuncionarioAmbientalController);

    PagfuncionarioAmbientalController.$inject = ['Pagfuncionario'];

    function PagfuncionarioAmbientalController(Pagfuncionario) {

        var vm = this;

        vm.pagfuncionarios = [];

        loadAll();

        function loadAll() {
            Pagfuncionario.query(function(result) {
                vm.pagfuncionarios = result;
                vm.searchQuery = null;
            });
        }
    }
})();
