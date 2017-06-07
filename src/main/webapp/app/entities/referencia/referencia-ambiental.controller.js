(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ReferenciaAmbientalController', ReferenciaAmbientalController);

    ReferenciaAmbientalController.$inject = ['Referencia'];

    function ReferenciaAmbientalController(Referencia) {

        var vm = this;

        vm.referencias = [];

        loadAll();

        function loadAll() {
            Referencia.query(function(result) {
                vm.referencias = result;
                vm.searchQuery = null;
            });
        }
    }
})();
