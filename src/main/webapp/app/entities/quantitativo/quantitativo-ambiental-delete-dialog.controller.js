(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('QuantitativoAmbientalDeleteController',QuantitativoAmbientalDeleteController);

    QuantitativoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Quantitativo'];

    function QuantitativoAmbientalDeleteController($uibModalInstance, entity, Quantitativo) {
        var vm = this;

        vm.quantitativo = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Quantitativo.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
