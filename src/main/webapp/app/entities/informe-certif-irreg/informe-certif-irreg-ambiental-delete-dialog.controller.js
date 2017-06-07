(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('InformeCertifIrregAmbientalDeleteController',InformeCertifIrregAmbientalDeleteController);

    InformeCertifIrregAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'InformeCertifIrreg'];

    function InformeCertifIrregAmbientalDeleteController($uibModalInstance, entity, InformeCertifIrreg) {
        var vm = this;

        vm.informeCertifIrreg = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            InformeCertifIrreg.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
