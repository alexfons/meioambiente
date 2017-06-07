(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciacertificadoirregularidadeAmbientalController', OcorrenciacertificadoirregularidadeAmbientalController);

    OcorrenciacertificadoirregularidadeAmbientalController.$inject = ['Ocorrenciacertificadoirregularidade'];

    function OcorrenciacertificadoirregularidadeAmbientalController(Ocorrenciacertificadoirregularidade) {

        var vm = this;

        vm.ocorrenciacertificadoirregularidades = [];

        loadAll();

        function loadAll() {
            Ocorrenciacertificadoirregularidade.query(function(result) {
                vm.ocorrenciacertificadoirregularidades = result;
                vm.searchQuery = null;
            });
        }
    }
})();
