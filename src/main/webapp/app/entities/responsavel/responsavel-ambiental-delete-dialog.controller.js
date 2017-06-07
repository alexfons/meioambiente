(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ResponsavelAmbientalDeleteController',ResponsavelAmbientalDeleteController);

    ResponsavelAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Responsavel'];

    function ResponsavelAmbientalDeleteController($uibModalInstance, entity, Responsavel) {
        var vm = this;

        vm.responsavel = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Responsavel.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
