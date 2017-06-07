(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('MovimentacaoAmbientalDeleteController',MovimentacaoAmbientalDeleteController);

    MovimentacaoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Movimentacao'];

    function MovimentacaoAmbientalDeleteController($uibModalInstance, entity, Movimentacao) {
        var vm = this;

        vm.movimentacao = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Movimentacao.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
