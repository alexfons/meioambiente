(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('LogAmbientalDialogController', LogAmbientalDialogController);

    LogAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Log', 'Usuario'];

    function LogAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Log, Usuario) {
        var vm = this;

        vm.log = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.usuarios = Usuario.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.log.id !== null) {
                Log.update(vm.log, onSaveSuccess, onSaveError);
            } else {
                Log.save(vm.log, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:logUpdate', result);
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
