(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ResidenteAmbientalDeleteController',ResidenteAmbientalDeleteController);

    ResidenteAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Residente'];

    function ResidenteAmbientalDeleteController($uibModalInstance, entity, Residente) {
        var vm = this;

        vm.residente = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Residente.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
