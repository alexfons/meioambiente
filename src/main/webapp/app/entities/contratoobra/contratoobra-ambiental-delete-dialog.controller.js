(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ContratoobraAmbientalDeleteController',ContratoobraAmbientalDeleteController);

    ContratoobraAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Contratoobra'];

    function ContratoobraAmbientalDeleteController($uibModalInstance, entity, Contratoobra) {
        var vm = this;

        vm.contratoobra = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Contratoobra.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
