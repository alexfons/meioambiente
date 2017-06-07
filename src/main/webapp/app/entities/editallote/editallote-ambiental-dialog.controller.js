(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('EditalloteAmbientalDialogController', EditalloteAmbientalDialogController);

    EditalloteAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Editallote', 'Edital'];

    function EditalloteAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Editallote, Edital) {
        var vm = this;

        vm.editallote = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.editals = Edital.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.editallote.id !== null) {
                Editallote.update(vm.editallote, onSaveSuccess, onSaveError);
            } else {
                Editallote.save(vm.editallote, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:editalloteUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dataprovacaolote = false;
        vm.datePickerOpenStatus.datarelatoriolote = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
