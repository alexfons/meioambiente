(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CertifConforAmbientalDeleteController',CertifConforAmbientalDeleteController);

    CertifConforAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'CertifConfor'];

    function CertifConforAmbientalDeleteController($uibModalInstance, entity, CertifConfor) {
        var vm = this;

        vm.certifConfor = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            CertifConfor.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
