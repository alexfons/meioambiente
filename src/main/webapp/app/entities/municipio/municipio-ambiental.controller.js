(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('MunicipioAmbientalController', MunicipioAmbientalController);

    MunicipioAmbientalController.$inject = ['Municipio'];

    function MunicipioAmbientalController(Municipio) {

        var vm = this;

        vm.municipios = [];

        loadAll();

        function loadAll() {
            Municipio.query(function(result) {
                vm.municipios = result;
                vm.searchQuery = null;
            });
        }
    }
})();
