(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ContratoprojetoAmbientalDeleteController',ContratoprojetoAmbientalDeleteController);

    ContratoprojetoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Contratoprojeto'];

    function ContratoprojetoAmbientalDeleteController($uibModalInstance, entity, Contratoprojeto) {
        var vm = this;

        vm.contratoprojeto = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Contratoprojeto.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
