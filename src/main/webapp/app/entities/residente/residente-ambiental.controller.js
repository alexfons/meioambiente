(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ResidenteAmbientalController', ResidenteAmbientalController);

    ResidenteAmbientalController.$inject = ['Residente'];

    function ResidenteAmbientalController(Residente) {

        var vm = this;

        vm.residentes = [];

        loadAll();

        function loadAll() {
            Residente.query(function(result) {
                vm.residentes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
