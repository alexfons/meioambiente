(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FotoAmbientalDeleteController',FotoAmbientalDeleteController);

    FotoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Foto'];

    function FotoAmbientalDeleteController($uibModalInstance, entity, Foto) {
        var vm = this;

        vm.foto = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Foto.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
