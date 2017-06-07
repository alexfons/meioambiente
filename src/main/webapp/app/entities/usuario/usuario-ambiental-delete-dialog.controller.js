(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('UsuarioAmbientalDeleteController',UsuarioAmbientalDeleteController);

    UsuarioAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Usuario'];

    function UsuarioAmbientalDeleteController($uibModalInstance, entity, Usuario) {
        var vm = this;

        vm.usuario = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Usuario.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
