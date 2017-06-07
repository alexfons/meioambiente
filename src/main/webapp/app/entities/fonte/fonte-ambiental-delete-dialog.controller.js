(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FonteAmbientalDeleteController',FonteAmbientalDeleteController);

    FonteAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Fonte'];

    function FonteAmbientalDeleteController($uibModalInstance, entity, Fonte) {
        var vm = this;

        vm.fonte = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Fonte.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
