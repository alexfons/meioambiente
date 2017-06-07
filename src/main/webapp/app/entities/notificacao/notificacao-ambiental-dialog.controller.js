(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('NotificacaoAmbientalDialogController', NotificacaoAmbientalDialogController);

    NotificacaoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Notificacao', 'Obra', 'Foto', 'Ocorrencianotificacao'];

    function NotificacaoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Notificacao, Obra, Foto, Ocorrencianotificacao) {
        var vm = this;

        vm.notificacao = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.obras = Obra.query();
        vm.fotos = Foto.query();
        vm.ocorrencianotificacaos = Ocorrencianotificacao.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.notificacao.id !== null) {
                Notificacao.update(vm.notificacao, onSaveSuccess, onSaveError);
            } else {
                Notificacao.save(vm.notificacao, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:notificacaoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.data = false;
        vm.datePickerOpenStatus.datainspecao = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
