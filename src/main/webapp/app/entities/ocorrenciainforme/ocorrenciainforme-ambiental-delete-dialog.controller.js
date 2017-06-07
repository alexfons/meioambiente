(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciainformeAmbientalDeleteController',OcorrenciainformeAmbientalDeleteController);

    OcorrenciainformeAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Ocorrenciainforme'];

    function OcorrenciainformeAmbientalDeleteController($uibModalInstance, entity, Ocorrenciainforme) {
        var vm = this;

        vm.ocorrenciainforme = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Ocorrenciainforme.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
