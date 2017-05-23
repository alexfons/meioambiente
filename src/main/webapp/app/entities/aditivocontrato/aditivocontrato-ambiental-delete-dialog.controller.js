(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AditivocontratoAmbientalDeleteController',AditivocontratoAmbientalDeleteController);

    AditivocontratoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Aditivocontrato'];

    function AditivocontratoAmbientalDeleteController($uibModalInstance, entity, Aditivocontrato) {
        var vm = this;

        vm.aditivocontrato = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Aditivocontrato.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
