(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CertificadoconformidadeAmbientalDeleteController',CertificadoconformidadeAmbientalDeleteController);

    CertificadoconformidadeAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Certificadoconformidade'];

    function CertificadoconformidadeAmbientalDeleteController($uibModalInstance, entity, Certificadoconformidade) {
        var vm = this;

        vm.certificadoconformidade = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Certificadoconformidade.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
