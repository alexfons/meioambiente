(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ResponsavelAmbientalController', ResponsavelAmbientalController);

    ResponsavelAmbientalController.$inject = ['Responsavel'];

    function ResponsavelAmbientalController(Responsavel) {

        var vm = this;

        vm.responsavels = [];

        loadAll();

        function loadAll() {
            Responsavel.query(function(result) {
                vm.responsavels = result;
                vm.searchQuery = null;
            });
        }
    }
})();
