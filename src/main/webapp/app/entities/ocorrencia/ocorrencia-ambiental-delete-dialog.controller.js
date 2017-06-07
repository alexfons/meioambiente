(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciaAmbientalDeleteController',OcorrenciaAmbientalDeleteController);

    OcorrenciaAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Ocorrencia'];

    function OcorrenciaAmbientalDeleteController($uibModalInstance, entity, Ocorrencia) {
        var vm = this;

        vm.ocorrencia = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Ocorrencia.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
