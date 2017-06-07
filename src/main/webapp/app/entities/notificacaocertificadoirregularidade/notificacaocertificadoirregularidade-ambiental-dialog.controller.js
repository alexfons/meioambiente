(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('NotificacaocertificadoirregularidadeAmbientalDialogController', NotificacaocertificadoirregularidadeAmbientalDialogController);

    NotificacaocertificadoirregularidadeAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Notificacaocertificadoirregularidade'];

    function NotificacaocertificadoirregularidadeAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Notificacaocertificadoirregularidade) {
        var vm = this;

        vm.notificacaocertificadoirregularidade = entity;
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
            if (vm.notificacaocertificadoirregularidade.id !== null) {
                Notificacaocertificadoirregularidade.update(vm.notificacaocertificadoirregularidade, onSaveSuccess, onSaveError);
            } else {
                Notificacaocertificadoirregularidade.save(vm.notificacaocertificadoirregularidade, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:notificacaocertificadoirregularidadeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
