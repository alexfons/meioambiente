(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoObraAmbientalController', TipoObraAmbientalController);

    TipoObraAmbientalController.$inject = ['TipoObra'];

    function TipoObraAmbientalController(TipoObra) {

        var vm = this;

        vm.tipoObras = [];

        loadAll();

        function loadAll() {
            TipoObra.query(function(result) {
                vm.tipoObras = result;
                vm.searchQuery = null;
            });
        }
    }
})();
