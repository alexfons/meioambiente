(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AdministrativoAmbientalDeleteController',AdministrativoAmbientalDeleteController);

    AdministrativoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Administrativo'];

    function AdministrativoAmbientalDeleteController($uibModalInstance, entity, Administrativo) {
        var vm = this;

        vm.administrativo = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Administrativo.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
