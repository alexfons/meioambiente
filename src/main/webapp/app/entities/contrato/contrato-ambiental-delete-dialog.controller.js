(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ContratoAmbientalDeleteController',ContratoAmbientalDeleteController);

    ContratoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Contrato'];

    function ContratoAmbientalDeleteController($uibModalInstance, entity, Contrato) {
        var vm = this;

        vm.contrato = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Contrato.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
