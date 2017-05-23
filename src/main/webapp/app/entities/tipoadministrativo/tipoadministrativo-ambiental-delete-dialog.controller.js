(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoadministrativoAmbientalDeleteController',TipoadministrativoAmbientalDeleteController);

    TipoadministrativoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Tipoadministrativo'];

    function TipoadministrativoAmbientalDeleteController($uibModalInstance, entity, Tipoadministrativo) {
        var vm = this;

        vm.tipoadministrativo = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Tipoadministrativo.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
