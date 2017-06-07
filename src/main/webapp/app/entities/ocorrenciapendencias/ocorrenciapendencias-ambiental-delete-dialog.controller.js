(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciapendenciasAmbientalDeleteController',OcorrenciapendenciasAmbientalDeleteController);

    OcorrenciapendenciasAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Ocorrenciapendencias'];

    function OcorrenciapendenciasAmbientalDeleteController($uibModalInstance, entity, Ocorrenciapendencias) {
        var vm = this;

        vm.ocorrenciapendencias = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Ocorrenciapendencias.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
