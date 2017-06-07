(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipocertificadoconformidadeAmbientalController', TipocertificadoconformidadeAmbientalController);

    TipocertificadoconformidadeAmbientalController.$inject = ['Tipocertificadoconformidade'];

    function TipocertificadoconformidadeAmbientalController(Tipocertificadoconformidade) {

        var vm = this;

        vm.tipocertificadoconformidades = [];

        loadAll();

        function loadAll() {
            Tipocertificadoconformidade.query(function(result) {
                vm.tipocertificadoconformidades = result;
                vm.searchQuery = null;
            });
        }
    }
})();
