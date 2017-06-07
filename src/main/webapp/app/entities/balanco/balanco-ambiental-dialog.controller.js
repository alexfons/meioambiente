(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('BalancoAmbientalDialogController', BalancoAmbientalDialogController);

    BalancoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Balanco'];

    function BalancoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Balanco) {
        var vm = this;

        vm.balanco = entity;
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
            if (vm.balanco.id !== null) {
                Balanco.update(vm.balanco, onSaveSuccess, onSaveError);
            } else {
                Balanco.save(vm.balanco, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:balancoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.datafim = false;
        vm.datePickerOpenStatus.datainicio = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
