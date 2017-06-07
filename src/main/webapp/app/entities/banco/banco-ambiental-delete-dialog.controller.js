(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('BancoAmbientalDeleteController',BancoAmbientalDeleteController);

    BancoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Banco'];

    function BancoAmbientalDeleteController($uibModalInstance, entity, Banco) {
        var vm = this;

        vm.banco = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Banco.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
