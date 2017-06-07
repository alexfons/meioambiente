(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoAmbientalDeleteController',TipoAmbientalDeleteController);

    TipoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Tipo'];

    function TipoAmbientalDeleteController($uibModalInstance, entity, Tipo) {
        var vm = this;

        vm.tipo = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Tipo.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
