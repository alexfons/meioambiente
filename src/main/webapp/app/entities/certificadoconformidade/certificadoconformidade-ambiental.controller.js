(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CertificadoconformidadeAmbientalController', CertificadoconformidadeAmbientalController);

    CertificadoconformidadeAmbientalController.$inject = ['Certificadoconformidade'];

    function CertificadoconformidadeAmbientalController(Certificadoconformidade) {

        var vm = this;

        vm.certificadoconformidades = [];

        loadAll();

        function loadAll() {
            Certificadoconformidade.query(function(result) {
                vm.certificadoconformidades = result;
                vm.searchQuery = null;
            });
        }
    }
})();
