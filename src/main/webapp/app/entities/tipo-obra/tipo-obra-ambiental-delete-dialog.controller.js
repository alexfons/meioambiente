(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoObraAmbientalDeleteController',TipoObraAmbientalDeleteController);

    TipoObraAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'TipoObra'];

    function TipoObraAmbientalDeleteController($uibModalInstance, entity, TipoObra) {
        var vm = this;

        vm.tipoObra = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            TipoObra.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
