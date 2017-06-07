(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrencianotificacaoAmbientalController', OcorrencianotificacaoAmbientalController);

    OcorrencianotificacaoAmbientalController.$inject = ['Ocorrencianotificacao'];

    function OcorrencianotificacaoAmbientalController(Ocorrencianotificacao) {

        var vm = this;

        vm.ocorrencianotificacaos = [];

        loadAll();

        function loadAll() {
            Ocorrencianotificacao.query(function(result) {
                vm.ocorrencianotificacaos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
