(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipocontratoAmbientalDeleteController',TipocontratoAmbientalDeleteController);

    TipocontratoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Tipocontrato'];

    function TipocontratoAmbientalDeleteController($uibModalInstance, entity, Tipocontrato) {
        var vm = this;

        vm.tipocontrato = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Tipocontrato.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
