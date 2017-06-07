(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ReferenciacontratoAmbientalController', ReferenciacontratoAmbientalController);

    ReferenciacontratoAmbientalController.$inject = ['Referenciacontrato'];

    function ReferenciacontratoAmbientalController(Referenciacontrato) {

        var vm = this;

        vm.referenciacontratoes = [];

        loadAll();

        function loadAll() {
            Referenciacontrato.query(function(result) {
                vm.referenciacontratoes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
