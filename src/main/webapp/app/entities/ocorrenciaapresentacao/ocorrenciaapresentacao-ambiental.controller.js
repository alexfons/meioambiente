(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciaapresentacaoAmbientalController', OcorrenciaapresentacaoAmbientalController);

    OcorrenciaapresentacaoAmbientalController.$inject = ['Ocorrenciaapresentacao'];

    function OcorrenciaapresentacaoAmbientalController(Ocorrenciaapresentacao) {

        var vm = this;

        vm.ocorrenciaapresentacaos = [];

        loadAll();

        function loadAll() {
            Ocorrenciaapresentacao.query(function(result) {
                vm.ocorrenciaapresentacaos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
