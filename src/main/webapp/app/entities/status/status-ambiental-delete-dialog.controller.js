(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('StatusAmbientalDeleteController',StatusAmbientalDeleteController);

    StatusAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Status'];

    function StatusAmbientalDeleteController($uibModalInstance, entity, Status) {
        var vm = this;

        vm.status = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Status.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
