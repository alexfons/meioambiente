(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CondicionanteAmbientalDeleteController',CondicionanteAmbientalDeleteController);

    CondicionanteAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Condicionante'];

    function CondicionanteAmbientalDeleteController($uibModalInstance, entity, Condicionante) {
        var vm = this;

        vm.condicionante = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Condicionante.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
