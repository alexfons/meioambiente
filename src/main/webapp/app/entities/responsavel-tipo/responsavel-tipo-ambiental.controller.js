(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ResponsavelTipoAmbientalController', ResponsavelTipoAmbientalController);

    ResponsavelTipoAmbientalController.$inject = ['ResponsavelTipo'];

    function ResponsavelTipoAmbientalController(ResponsavelTipo) {

        var vm = this;

        vm.responsavelTipos = [];

        loadAll();

        function loadAll() {
            ResponsavelTipo.query(function(result) {
                vm.responsavelTipos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
