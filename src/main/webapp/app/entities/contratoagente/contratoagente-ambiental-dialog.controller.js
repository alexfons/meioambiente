(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ContratoagenteAmbientalDialogController', ContratoagenteAmbientalDialogController);

    ContratoagenteAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Contratoagente'];

    function ContratoagenteAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Contratoagente) {
        var vm = this;

        vm.contratoagente = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.contratoagente.id !== null) {
                Contratoagente.update(vm.contratoagente, onSaveSuccess, onSaveError);
            } else {
                Contratoagente.save(vm.contratoagente, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:contratoagenteUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dataaprovacao = false;
        vm.datePickerOpenStatus.dataassinatura = false;
        vm.datePickerOpenStatus.dataconclusao = false;
        vm.datePickerOpenStatus.datainicio = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
