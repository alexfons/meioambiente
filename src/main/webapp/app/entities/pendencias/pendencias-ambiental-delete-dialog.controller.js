(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('PendenciasAmbientalDeleteController',PendenciasAmbientalDeleteController);

    PendenciasAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Pendencias'];

    function PendenciasAmbientalDeleteController($uibModalInstance, entity, Pendencias) {
        var vm = this;

        vm.pendencias = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Pendencias.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
