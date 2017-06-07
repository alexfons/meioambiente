(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('MedicaoAmbientalController', MedicaoAmbientalController);

    MedicaoAmbientalController.$inject = ['Medicao'];

    function MedicaoAmbientalController(Medicao) {

        var vm = this;

        vm.medicaos = [];

        loadAll();

        function loadAll() {
            Medicao.query(function(result) {
                vm.medicaos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
