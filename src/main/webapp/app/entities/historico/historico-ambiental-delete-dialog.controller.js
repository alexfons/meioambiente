(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('HistoricoAmbientalDeleteController',HistoricoAmbientalDeleteController);

    HistoricoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Historico'];

    function HistoricoAmbientalDeleteController($uibModalInstance, entity, Historico) {
        var vm = this;

        vm.historico = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Historico.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
