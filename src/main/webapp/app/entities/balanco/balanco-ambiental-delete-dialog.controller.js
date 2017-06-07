(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('BalancoAmbientalDeleteController',BalancoAmbientalDeleteController);

    BalancoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Balanco'];

    function BalancoAmbientalDeleteController($uibModalInstance, entity, Balanco) {
        var vm = this;

        vm.balanco = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Balanco.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
