(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('PlanocontasAmbientalDeleteController',PlanocontasAmbientalDeleteController);

    PlanocontasAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Planocontas'];

    function PlanocontasAmbientalDeleteController($uibModalInstance, entity, Planocontas) {
        var vm = this;

        vm.planocontas = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Planocontas.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
