(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ValoresdesembolsoAmbientalController', ValoresdesembolsoAmbientalController);

    ValoresdesembolsoAmbientalController.$inject = ['Valoresdesembolso'];

    function ValoresdesembolsoAmbientalController(Valoresdesembolso) {

        var vm = this;

        vm.valoresdesembolsos = [];

        loadAll();

        function loadAll() {
            Valoresdesembolso.query(function(result) {
                vm.valoresdesembolsos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
