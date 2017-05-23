(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('EmpresaAmbientalDeleteController',EmpresaAmbientalDeleteController);

    EmpresaAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Empresa'];

    function EmpresaAmbientalDeleteController($uibModalInstance, entity, Empresa) {
        var vm = this;

        vm.empresa = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Empresa.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
