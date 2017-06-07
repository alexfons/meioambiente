(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoAmbientalController', TipoAmbientalController);

    TipoAmbientalController.$inject = ['Tipo'];

    function TipoAmbientalController(Tipo) {

        var vm = this;

        vm.tipos = [];

        loadAll();

        function loadAll() {
            Tipo.query(function(result) {
                vm.tipos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
