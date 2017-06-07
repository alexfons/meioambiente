(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('InformecertificadoirregularidadeAmbientalController', InformecertificadoirregularidadeAmbientalController);

    InformecertificadoirregularidadeAmbientalController.$inject = ['Informecertificadoirregularidade'];

    function InformecertificadoirregularidadeAmbientalController(Informecertificadoirregularidade) {

        var vm = this;

        vm.informecertificadoirregularidades = [];

        loadAll();

        function loadAll() {
            Informecertificadoirregularidade.query(function(result) {
                vm.informecertificadoirregularidades = result;
                vm.searchQuery = null;
            });
        }
    }
})();
