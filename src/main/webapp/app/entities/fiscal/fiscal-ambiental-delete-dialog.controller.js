(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FiscalAmbientalDeleteController',FiscalAmbientalDeleteController);

    FiscalAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Fiscal'];

    function FiscalAmbientalDeleteController($uibModalInstance, entity, Fiscal) {
        var vm = this;

        vm.fiscal = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Fiscal.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
