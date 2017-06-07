(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ListamovimentacaoAmbientalDeleteController',ListamovimentacaoAmbientalDeleteController);

    ListamovimentacaoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Listamovimentacao'];

    function ListamovimentacaoAmbientalDeleteController($uibModalInstance, entity, Listamovimentacao) {
        var vm = this;

        vm.listamovimentacao = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Listamovimentacao.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
