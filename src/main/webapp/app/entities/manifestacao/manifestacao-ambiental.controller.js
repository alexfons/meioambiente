(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ManifestacaoAmbientalController', ManifestacaoAmbientalController);

    ManifestacaoAmbientalController.$inject = ['Manifestacao'];

    function ManifestacaoAmbientalController(Manifestacao) {

        var vm = this;

        vm.manifestacaos = [];

        loadAll();

        function loadAll() {
            Manifestacao.query(function(result) {
                vm.manifestacaos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
