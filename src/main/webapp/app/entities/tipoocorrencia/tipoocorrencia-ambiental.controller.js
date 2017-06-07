(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoocorrenciaAmbientalController', TipoocorrenciaAmbientalController);

    TipoocorrenciaAmbientalController.$inject = ['Tipoocorrencia'];

    function TipoocorrenciaAmbientalController(Tipoocorrencia) {

        var vm = this;

        vm.tipoocorrencias = [];

        loadAll();

        function loadAll() {
            Tipoocorrencia.query(function(result) {
                vm.tipoocorrencias = result;
                vm.searchQuery = null;
            });
        }
    }
})();
