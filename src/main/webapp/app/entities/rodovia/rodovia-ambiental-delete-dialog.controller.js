(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('RodoviaAmbientalDeleteController',RodoviaAmbientalDeleteController);

    RodoviaAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Rodovia'];

    function RodoviaAmbientalDeleteController($uibModalInstance, entity, Rodovia) {
        var vm = this;

        vm.rodovia = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Rodovia.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
