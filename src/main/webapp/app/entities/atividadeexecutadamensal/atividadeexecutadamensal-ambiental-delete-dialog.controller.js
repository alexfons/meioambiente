(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AtividadeexecutadamensalAmbientalDeleteController',AtividadeexecutadamensalAmbientalDeleteController);

    AtividadeexecutadamensalAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Atividadeexecutadamensal'];

    function AtividadeexecutadamensalAmbientalDeleteController($uibModalInstance, entity, Atividadeexecutadamensal) {
        var vm = this;

        vm.atividadeexecutadamensal = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Atividadeexecutadamensal.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
