(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('LicencaAmbientalDeleteController',LicencaAmbientalDeleteController);

    LicencaAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Licenca'];

    function LicencaAmbientalDeleteController($uibModalInstance, entity, Licenca) {
        var vm = this;

        vm.licenca = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Licenca.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
