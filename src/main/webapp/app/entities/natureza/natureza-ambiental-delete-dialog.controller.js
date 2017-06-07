(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('NaturezaAmbientalDeleteController',NaturezaAmbientalDeleteController);

    NaturezaAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Natureza'];

    function NaturezaAmbientalDeleteController($uibModalInstance, entity, Natureza) {
        var vm = this;

        vm.natureza = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Natureza.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
