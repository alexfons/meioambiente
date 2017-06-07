(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ObraatividadeAmbientalController', ObraatividadeAmbientalController);

    ObraatividadeAmbientalController.$inject = ['Obraatividade'];

    function ObraatividadeAmbientalController(Obraatividade) {

        var vm = this;

        vm.obraatividades = [];

        loadAll();

        function loadAll() {
            Obraatividade.query(function(result) {
                vm.obraatividades = result;
                vm.searchQuery = null;
            });
        }
    }
})();
