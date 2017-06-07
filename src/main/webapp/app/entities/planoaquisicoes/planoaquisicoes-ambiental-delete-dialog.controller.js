(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('PlanoaquisicoesAmbientalDeleteController',PlanoaquisicoesAmbientalDeleteController);

    PlanoaquisicoesAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Planoaquisicoes'];

    function PlanoaquisicoesAmbientalDeleteController($uibModalInstance, entity, Planoaquisicoes) {
        var vm = this;

        vm.planoaquisicoes = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Planoaquisicoes.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
