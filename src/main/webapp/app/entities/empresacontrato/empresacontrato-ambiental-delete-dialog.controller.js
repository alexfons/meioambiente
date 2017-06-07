(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('EmpresacontratoAmbientalDeleteController',EmpresacontratoAmbientalDeleteController);

    EmpresacontratoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Empresacontrato'];

    function EmpresacontratoAmbientalDeleteController($uibModalInstance, entity, Empresacontrato) {
        var vm = this;

        vm.empresacontrato = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Empresacontrato.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
