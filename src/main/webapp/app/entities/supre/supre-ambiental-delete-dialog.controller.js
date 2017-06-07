(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('SupreAmbientalDeleteController',SupreAmbientalDeleteController);

    SupreAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Supre'];

    function SupreAmbientalDeleteController($uibModalInstance, entity, Supre) {
        var vm = this;

        vm.supre = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Supre.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
