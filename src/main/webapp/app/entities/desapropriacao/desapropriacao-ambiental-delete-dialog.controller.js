(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('DesapropriacaoAmbientalDeleteController',DesapropriacaoAmbientalDeleteController);

    DesapropriacaoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Desapropriacao'];

    function DesapropriacaoAmbientalDeleteController($uibModalInstance, entity, Desapropriacao) {
        var vm = this;

        vm.desapropriacao = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Desapropriacao.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
