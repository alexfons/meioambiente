(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ContabancariaAmbientalDeleteController',ContabancariaAmbientalDeleteController);

    ContabancariaAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Contabancaria'];

    function ContabancariaAmbientalDeleteController($uibModalInstance, entity, Contabancaria) {
        var vm = this;

        vm.contabancaria = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Contabancaria.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
