(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipocontratoAmbientalController', TipocontratoAmbientalController);

    TipocontratoAmbientalController.$inject = ['Tipocontrato'];

    function TipocontratoAmbientalController(Tipocontrato) {

        var vm = this;

        vm.tipocontratoes = [];

        loadAll();

        function loadAll() {
            Tipocontrato.query(function(result) {
                vm.tipocontratoes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
