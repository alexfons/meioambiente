(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FisicoAmbientalDeleteController',FisicoAmbientalDeleteController);

    FisicoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Fisico'];

    function FisicoAmbientalDeleteController($uibModalInstance, entity, Fisico) {
        var vm = this;

        vm.fisico = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Fisico.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
