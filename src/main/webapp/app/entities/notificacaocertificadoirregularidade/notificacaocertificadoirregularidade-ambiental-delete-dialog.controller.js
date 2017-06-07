(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('NotificacaocertificadoirregularidadeAmbientalDeleteController',NotificacaocertificadoirregularidadeAmbientalDeleteController);

    NotificacaocertificadoirregularidadeAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Notificacaocertificadoirregularidade'];

    function NotificacaocertificadoirregularidadeAmbientalDeleteController($uibModalInstance, entity, Notificacaocertificadoirregularidade) {
        var vm = this;

        vm.notificacaocertificadoirregularidade = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Notificacaocertificadoirregularidade.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
