(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ReferenciacontratoAmbientalDeleteController',ReferenciacontratoAmbientalDeleteController);

    ReferenciacontratoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Referenciacontrato'];

    function ReferenciacontratoAmbientalDeleteController($uibModalInstance, entity, Referenciacontrato) {
        var vm = this;

        vm.referenciacontrato = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Referenciacontrato.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
