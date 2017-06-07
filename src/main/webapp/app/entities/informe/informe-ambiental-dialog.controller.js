(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('InformeAmbientalDialogController', InformeAmbientalDialogController);

    InformeAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Informe', 'Obra', 'Foto', 'Ocorrenciainforme'];

    function InformeAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Informe, Obra, Foto, Ocorrenciainforme) {
        var vm = this;

        vm.informe = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.obras = Obra.query();
        vm.fotos = Foto.query();
        vm.ocorrenciainformes = Ocorrenciainforme.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.informe.id !== null) {
                Informe.update(vm.informe, onSaveSuccess, onSaveError);
            } else {
                Informe.save(vm.informe, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:informeUpdate', result);
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
