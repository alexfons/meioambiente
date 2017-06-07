(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('NotificacaoCertifIrregAmbientalDeleteController',NotificacaoCertifIrregAmbientalDeleteController);

    NotificacaoCertifIrregAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'NotificacaoCertifIrreg'];

    function NotificacaoCertifIrregAmbientalDeleteController($uibModalInstance, entity, NotificacaoCertifIrreg) {
        var vm = this;

        vm.notificacaoCertifIrreg = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            NotificacaoCertifIrreg.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
