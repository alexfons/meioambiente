(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ScriptAmbientalDialogController', ScriptAmbientalDialogController);

    ScriptAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Script'];

    function ScriptAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Script) {
        var vm = this;

        vm.script = entity;
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
            if (vm.script.id !== null) {
                Script.update(vm.script, onSaveSuccess, onSaveError);
            } else {
                Script.save(vm.script, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:scriptUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dataVerificacaoLicencas = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
