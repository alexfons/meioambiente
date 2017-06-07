(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ContratoagenteAmbientalDeleteController',ContratoagenteAmbientalDeleteController);

    ContratoagenteAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Contratoagente'];

    function ContratoagenteAmbientalDeleteController($uibModalInstance, entity, Contratoagente) {
        var vm = this;

        vm.contratoagente = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Contratoagente.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
