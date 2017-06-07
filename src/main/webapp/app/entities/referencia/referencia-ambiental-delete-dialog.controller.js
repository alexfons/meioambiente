(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ReferenciaAmbientalDeleteController',ReferenciaAmbientalDeleteController);

    ReferenciaAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Referencia'];

    function ReferenciaAmbientalDeleteController($uibModalInstance, entity, Referencia) {
        var vm = this;

        vm.referencia = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Referencia.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
