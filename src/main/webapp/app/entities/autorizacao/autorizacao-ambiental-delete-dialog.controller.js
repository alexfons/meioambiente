(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AutorizacaoAmbientalDeleteController',AutorizacaoAmbientalDeleteController);

    AutorizacaoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Autorizacao'];

    function AutorizacaoAmbientalDeleteController($uibModalInstance, entity, Autorizacao) {
        var vm = this;

        vm.autorizacao = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Autorizacao.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
