(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AditivocontratoAmbientalDialogController', AditivocontratoAmbientalDialogController);

    AditivocontratoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Aditivocontrato'];

    function AditivocontratoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Aditivocontrato) {
        var vm = this;

        vm.aditivocontrato = entity;
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
            if (vm.aditivocontrato.id !== null) {
                Aditivocontrato.update(vm.aditivocontrato, onSaveSuccess, onSaveError);
            } else {
                Aditivocontrato.save(vm.aditivocontrato, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:aditivocontratoUpdate', result);
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
