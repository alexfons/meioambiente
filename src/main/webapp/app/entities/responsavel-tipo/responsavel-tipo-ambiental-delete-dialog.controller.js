(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ResponsavelTipoAmbientalDeleteController',ResponsavelTipoAmbientalDeleteController);

    ResponsavelTipoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'ResponsavelTipo'];

    function ResponsavelTipoAmbientalDeleteController($uibModalInstance, entity, ResponsavelTipo) {
        var vm = this;

        vm.responsavelTipo = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ResponsavelTipo.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
