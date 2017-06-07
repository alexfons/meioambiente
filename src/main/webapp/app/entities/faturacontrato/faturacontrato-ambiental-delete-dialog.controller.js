(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FaturacontratoAmbientalDeleteController',FaturacontratoAmbientalDeleteController);

    FaturacontratoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Faturacontrato'];

    function FaturacontratoAmbientalDeleteController($uibModalInstance, entity, Faturacontrato) {
        var vm = this;

        vm.faturacontrato = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Faturacontrato.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
