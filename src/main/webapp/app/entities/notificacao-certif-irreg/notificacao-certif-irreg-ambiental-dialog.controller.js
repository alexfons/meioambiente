(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('NotificacaoCertifIrregAmbientalDialogController', NotificacaoCertifIrregAmbientalDialogController);

    NotificacaoCertifIrregAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'NotificacaoCertifIrreg'];

    function NotificacaoCertifIrregAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, NotificacaoCertifIrreg) {
        var vm = this;

        vm.notificacaoCertifIrreg = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.notificacaoCertifIrreg.id !== null) {
                NotificacaoCertifIrreg.update(vm.notificacaoCertifIrreg, onSaveSuccess, onSaveError);
            } else {
                NotificacaoCertifIrreg.save(vm.notificacaoCertifIrreg, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:notificacaoCertifIrregUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
