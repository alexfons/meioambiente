(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ObrafisicomensalAmbientalDeleteController',ObrafisicomensalAmbientalDeleteController);

    ObrafisicomensalAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Obrafisicomensal'];

    function ObrafisicomensalAmbientalDeleteController($uibModalInstance, entity, Obrafisicomensal) {
        var vm = this;

        vm.obrafisicomensal = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Obrafisicomensal.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
