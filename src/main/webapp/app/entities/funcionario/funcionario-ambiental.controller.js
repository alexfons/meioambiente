(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FuncionarioAmbientalController', FuncionarioAmbientalController);

    FuncionarioAmbientalController.$inject = ['Funcionario'];

    function FuncionarioAmbientalController(Funcionario) {

        var vm = this;

        vm.funcionarios = [];

        loadAll();

        function loadAll() {
            Funcionario.query(function(result) {
                vm.funcionarios = result;
                vm.searchQuery = null;
            });
        }
    }
})();
