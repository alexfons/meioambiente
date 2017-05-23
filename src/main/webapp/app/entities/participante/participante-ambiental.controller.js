(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ParticipanteAmbientalController', ParticipanteAmbientalController);

    ParticipanteAmbientalController.$inject = ['Participante'];

    function ParticipanteAmbientalController(Participante) {

        var vm = this;

        vm.participantes = [];

        loadAll();

        function loadAll() {
            Participante.query(function(result) {
                vm.participantes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
