(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('InformeAmbientalController', InformeAmbientalController);

    InformeAmbientalController.$inject = ['Informe'];

    function InformeAmbientalController(Informe) {

        var vm = this;

        vm.informes = [];

        loadAll();

        function loadAll() {
            Informe.query(function(result) {
                vm.informes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
