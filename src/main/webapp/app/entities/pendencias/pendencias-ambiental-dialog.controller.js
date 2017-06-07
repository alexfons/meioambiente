(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('PendenciasAmbientalDialogController', PendenciasAmbientalDialogController);

    PendenciasAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Pendencias', 'Obra', 'Foto', 'Ocorrenciapendencias'];

    function PendenciasAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Pendencias, Obra, Foto, Ocorrenciapendencias) {
        var vm = this;

        vm.pendencias = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.obras = Obra.query();
        vm.fotos = Foto.query();
        vm.ocorrenciapendencias = Ocorrenciapendencias.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.pendencias.id !== null) {
                Pendencias.update(vm.pendencias, onSaveSuccess, onSaveError);
            } else {
                Pendencias.save(vm.pendencias, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:pendenciasUpdate', result);
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
