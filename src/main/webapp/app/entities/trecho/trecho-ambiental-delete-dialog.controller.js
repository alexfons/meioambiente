(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TrechoAmbientalDeleteController',TrechoAmbientalDeleteController);

    TrechoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Trecho'];

    function TrechoAmbientalDeleteController($uibModalInstance, entity, Trecho) {
        var vm = this;

        vm.trecho = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Trecho.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
