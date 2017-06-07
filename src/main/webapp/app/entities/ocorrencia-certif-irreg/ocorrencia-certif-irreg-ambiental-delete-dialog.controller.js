(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciaCertifIrregAmbientalDeleteController',OcorrenciaCertifIrregAmbientalDeleteController);

    OcorrenciaCertifIrregAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'OcorrenciaCertifIrreg'];

    function OcorrenciaCertifIrregAmbientalDeleteController($uibModalInstance, entity, OcorrenciaCertifIrreg) {
        var vm = this;

        vm.ocorrenciaCertifIrreg = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            OcorrenciaCertifIrreg.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
