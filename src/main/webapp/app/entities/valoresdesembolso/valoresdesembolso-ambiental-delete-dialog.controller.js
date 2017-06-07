(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ValoresdesembolsoAmbientalDeleteController',ValoresdesembolsoAmbientalDeleteController);

    ValoresdesembolsoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Valoresdesembolso'];

    function ValoresdesembolsoAmbientalDeleteController($uibModalInstance, entity, Valoresdesembolso) {
        var vm = this;

        vm.valoresdesembolso = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Valoresdesembolso.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
