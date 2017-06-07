(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciainformeAmbientalController', OcorrenciainformeAmbientalController);

    OcorrenciainformeAmbientalController.$inject = ['Ocorrenciainforme'];

    function OcorrenciainformeAmbientalController(Ocorrenciainforme) {

        var vm = this;

        vm.ocorrenciainformes = [];

        loadAll();

        function loadAll() {
            Ocorrenciainforme.query(function(result) {
                vm.ocorrenciainformes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
