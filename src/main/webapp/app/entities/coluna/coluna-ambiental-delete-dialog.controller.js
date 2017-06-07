(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ColunaAmbientalDeleteController',ColunaAmbientalDeleteController);

    ColunaAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Coluna'];

    function ColunaAmbientalDeleteController($uibModalInstance, entity, Coluna) {
        var vm = this;

        vm.coluna = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Coluna.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
