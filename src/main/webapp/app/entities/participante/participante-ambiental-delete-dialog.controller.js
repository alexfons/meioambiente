(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ParticipanteAmbientalDeleteController',ParticipanteAmbientalDeleteController);

    ParticipanteAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Participante'];

    function ParticipanteAmbientalDeleteController($uibModalInstance, entity, Participante) {
        var vm = this;

        vm.participante = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Participante.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
