(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('RegistroAmbientalDeleteController',RegistroAmbientalDeleteController);

    RegistroAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Registro'];

    function RegistroAmbientalDeleteController($uibModalInstance, entity, Registro) {
        var vm = this;

        vm.registro = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Registro.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
