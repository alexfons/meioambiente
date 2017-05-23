(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('InspetorAmbientalDeleteController',InspetorAmbientalDeleteController);

    InspetorAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Inspetor'];

    function InspetorAmbientalDeleteController($uibModalInstance, entity, Inspetor) {
        var vm = this;

        vm.inspetor = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Inspetor.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
