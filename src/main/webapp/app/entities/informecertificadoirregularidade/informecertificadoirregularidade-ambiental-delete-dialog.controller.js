(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('InformecertificadoirregularidadeAmbientalDeleteController',InformecertificadoirregularidadeAmbientalDeleteController);

    InformecertificadoirregularidadeAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Informecertificadoirregularidade'];

    function InformecertificadoirregularidadeAmbientalDeleteController($uibModalInstance, entity, Informecertificadoirregularidade) {
        var vm = this;

        vm.informecertificadoirregularidade = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Informecertificadoirregularidade.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
