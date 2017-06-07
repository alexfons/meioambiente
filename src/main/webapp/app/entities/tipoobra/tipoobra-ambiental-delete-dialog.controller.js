(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoobraAmbientalDeleteController',TipoobraAmbientalDeleteController);

    TipoobraAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Tipoobra'];

    function TipoobraAmbientalDeleteController($uibModalInstance, entity, Tipoobra) {
        var vm = this;

        vm.tipoobra = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Tipoobra.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
