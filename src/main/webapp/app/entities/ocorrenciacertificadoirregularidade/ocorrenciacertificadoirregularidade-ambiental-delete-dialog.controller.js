(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciacertificadoirregularidadeAmbientalDeleteController',OcorrenciacertificadoirregularidadeAmbientalDeleteController);

    OcorrenciacertificadoirregularidadeAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Ocorrenciacertificadoirregularidade'];

    function OcorrenciacertificadoirregularidadeAmbientalDeleteController($uibModalInstance, entity, Ocorrenciacertificadoirregularidade) {
        var vm = this;

        vm.ocorrenciacertificadoirregularidade = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Ocorrenciacertificadoirregularidade.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
