(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('LancamentosAmbientalDeleteController',LancamentosAmbientalDeleteController);

    LancamentosAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Lancamentos'];

    function LancamentosAmbientalDeleteController($uibModalInstance, entity, Lancamentos) {
        var vm = this;

        vm.lancamentos = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Lancamentos.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
