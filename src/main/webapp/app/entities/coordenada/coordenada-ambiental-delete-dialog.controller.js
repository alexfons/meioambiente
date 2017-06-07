(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CoordenadaAmbientalDeleteController',CoordenadaAmbientalDeleteController);

    CoordenadaAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Coordenada'];

    function CoordenadaAmbientalDeleteController($uibModalInstance, entity, Coordenada) {
        var vm = this;

        vm.coordenada = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Coordenada.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
