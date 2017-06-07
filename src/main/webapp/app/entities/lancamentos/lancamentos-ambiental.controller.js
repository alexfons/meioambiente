(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('LancamentosAmbientalController', LancamentosAmbientalController);

    LancamentosAmbientalController.$inject = ['Lancamentos'];

    function LancamentosAmbientalController(Lancamentos) {

        var vm = this;

        vm.lancamentos = [];

        loadAll();

        function loadAll() {
            Lancamentos.query(function(result) {
                vm.lancamentos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
