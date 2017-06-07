(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('EditalAmbientalDeleteController',EditalAmbientalDeleteController);

    EditalAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Edital'];

    function EditalAmbientalDeleteController($uibModalInstance, entity, Edital) {
        var vm = this;

        vm.edital = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Edital.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
