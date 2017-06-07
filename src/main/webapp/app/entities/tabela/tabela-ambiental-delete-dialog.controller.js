(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TabelaAmbientalDeleteController',TabelaAmbientalDeleteController);

    TabelaAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Tabela'];

    function TabelaAmbientalDeleteController($uibModalInstance, entity, Tabela) {
        var vm = this;

        vm.tabela = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Tabela.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
