(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ContratoprojetoAmbientalController', ContratoprojetoAmbientalController);

    ContratoprojetoAmbientalController.$inject = ['Contratoprojeto'];

    function ContratoprojetoAmbientalController(Contratoprojeto) {

        var vm = this;

        vm.contratoprojetos = [];

        loadAll();

        function loadAll() {
            Contratoprojeto.query(function(result) {
                vm.contratoprojetos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
