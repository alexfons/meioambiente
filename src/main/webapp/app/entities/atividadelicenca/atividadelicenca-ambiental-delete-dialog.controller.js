(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AtividadelicencaAmbientalDeleteController',AtividadelicencaAmbientalDeleteController);

    AtividadelicencaAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Atividadelicenca'];

    function AtividadelicencaAmbientalDeleteController($uibModalInstance, entity, Atividadelicenca) {
        var vm = this;

        vm.atividadelicenca = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Atividadelicenca.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
