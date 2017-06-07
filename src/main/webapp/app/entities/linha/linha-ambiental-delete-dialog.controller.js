(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('LinhaAmbientalDeleteController',LinhaAmbientalDeleteController);

    LinhaAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Linha'];

    function LinhaAmbientalDeleteController($uibModalInstance, entity, Linha) {
        var vm = this;

        vm.linha = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Linha.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
