(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciaAmbientalController', OcorrenciaAmbientalController);

    OcorrenciaAmbientalController.$inject = ['Ocorrencia'];

    function OcorrenciaAmbientalController(Ocorrencia) {

        var vm = this;

        vm.ocorrencias = [];

        loadAll();

        function loadAll() {
            Ocorrencia.query(function(result) {
                vm.ocorrencias = result;
                vm.searchQuery = null;
            });
        }
    }
})();
