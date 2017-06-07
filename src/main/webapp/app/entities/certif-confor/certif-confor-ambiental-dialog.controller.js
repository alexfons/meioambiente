(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CertifConforAmbientalDialogController', CertifConforAmbientalDialogController);

    CertifConforAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CertifConfor', 'Obra', 'TipocertifConfor', 'InformeCertifIrreg', 'NotificacaoCertifIrreg', 'OcorrenciaCertifIrreg'];

    function CertifConforAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CertifConfor, Obra, TipocertifConfor, InformeCertifIrreg, NotificacaoCertifIrreg, OcorrenciaCertifIrreg) {
        var vm = this;

        vm.certifConfor = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.obras = Obra.query();
        vm.tipocertifconfors = TipocertifConfor.query();
        vm.informecertifirregs = InformeCertifIrreg.query();
        vm.notificacaocertifirregs = NotificacaoCertifIrreg.query();
        vm.ocorrenciacertifirregs = OcorrenciaCertifIrreg.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.certifConfor.id !== null) {
                CertifConfor.update(vm.certifConfor, onSaveSuccess, onSaveError);
            } else {
                CertifConfor.save(vm.certifConfor, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:certifConforUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.data = false;
        vm.datePickerOpenStatus.dataliberacao = false;
        vm.datePickerOpenStatus.dataparalisacao = false;
        vm.datePickerOpenStatus.datareinicio = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
