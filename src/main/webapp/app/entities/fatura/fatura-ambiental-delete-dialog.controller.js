(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FaturaAmbientalDeleteController',FaturaAmbientalDeleteController);

    FaturaAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Fatura'];

    function FaturaAmbientalDeleteController($uibModalInstance, entity, Fatura) {
        var vm = this;

        vm.fatura = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Fatura.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
