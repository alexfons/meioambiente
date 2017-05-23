(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AucAmbientalDeleteController',AucAmbientalDeleteController);

    AucAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Auc'];

    function AucAmbientalDeleteController($uibModalInstance, entity, Auc) {
        var vm = this;

        vm.auc = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Auc.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
