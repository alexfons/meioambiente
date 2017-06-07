(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciaAmbientalDialogController', OcorrenciaAmbientalDialogController);

    OcorrenciaAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Ocorrencia', 'Atividade', 'Obra', 'Tabela', 'Tipoocorrencia', 'Foto', 'Historico', 'Registro'];

    function OcorrenciaAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Ocorrencia, Atividade, Obra, Tabela, Tipoocorrencia, Foto, Historico, Registro) {
        var vm = this;

        vm.ocorrencia = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.atividades = Atividade.query();
        vm.obras = Obra.query();
        vm.tabelas = Tabela.query();
        vm.tipoocorrencias = Tipoocorrencia.query();
        vm.fotos = Foto.query();
        vm.historicos = Historico.query();
        vm.registros = Registro.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.ocorrencia.id !== null) {
                Ocorrencia.update(vm.ocorrencia, onSaveSuccess, onSaveError);
            } else {
                Ocorrencia.save(vm.ocorrencia, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:ocorrenciaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.data = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
