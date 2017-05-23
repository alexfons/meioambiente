(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ObraAmbientalDeleteController',ObraAmbientalDeleteController);

    ObraAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Obra'];

    function ObraAmbientalDeleteController($uibModalInstance, entity, Obra) {
        var vm = this;

        vm.obra = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Obra.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
