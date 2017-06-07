(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CertificadoconformidadeAmbientalDialogController', CertificadoconformidadeAmbientalDialogController);

    CertificadoconformidadeAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Certificadoconformidade', 'Obra', 'Tipocertificadoconformidade', 'Informecertificadoirregularidade', 'Notificacaocertificadoirregularidade', 'Ocorrenciacertificadoirregularidade'];

    function CertificadoconformidadeAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Certificadoconformidade, Obra, Tipocertificadoconformidade, Informecertificadoirregularidade, Notificacaocertificadoirregularidade, Ocorrenciacertificadoirregularidade) {
        var vm = this;

        vm.certificadoconformidade = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.obras = Obra.query();
        vm.tipocertificadoconformidades = Tipocertificadoconformidade.query();
        vm.informecertificadoirregularidades = Informecertificadoirregularidade.query();
        vm.notificacaocertificadoirregularidades = Notificacaocertificadoirregularidade.query();
        vm.ocorrenciacertificadoirregularidades = Ocorrenciacertificadoirregularidade.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.certificadoconformidade.id !== null) {
                Certificadoconformidade.update(vm.certificadoconformidade, onSaveSuccess, onSaveError);
            } else {
                Certificadoconformidade.save(vm.certificadoconformidade, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:certificadoconformidadeUpdate', result);
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
