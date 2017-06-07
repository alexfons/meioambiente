(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('NotificacaoAmbientalDeleteController',NotificacaoAmbientalDeleteController);

    NotificacaoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Notificacao'];

    function NotificacaoAmbientalDeleteController($uibModalInstance, entity, Notificacao) {
        var vm = this;

        vm.notificacao = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Notificacao.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
