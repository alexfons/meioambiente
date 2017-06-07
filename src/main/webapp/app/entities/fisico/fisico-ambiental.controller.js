(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FisicoAmbientalController', FisicoAmbientalController);

    FisicoAmbientalController.$inject = ['Fisico'];

    function FisicoAmbientalController(Fisico) {

        var vm = this;

        vm.fisicos = [];

        loadAll();

        function loadAll() {
            Fisico.query(function(result) {
                vm.fisicos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
