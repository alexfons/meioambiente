(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoadministrativoAmbientalController', TipoadministrativoAmbientalController);

    TipoadministrativoAmbientalController.$inject = ['Tipoadministrativo'];

    function TipoadministrativoAmbientalController(Tipoadministrativo) {

        var vm = this;

        vm.tipoadministrativos = [];

        loadAll();

        function loadAll() {
            Tipoadministrativo.query(function(result) {
                vm.tipoadministrativos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
