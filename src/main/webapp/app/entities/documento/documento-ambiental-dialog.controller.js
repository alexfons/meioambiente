(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('DocumentoAmbientalDialogController', DocumentoAmbientalDialogController);

    DocumentoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Documento'];

    function DocumentoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Documento) {
        var vm = this;

        vm.documento = entity;
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
            if (vm.documento.id !== null) {
                Documento.update(vm.documento, onSaveSuccess, onSaveError);
            } else {
                Documento.save(vm.documento, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:documentoUpdate', result);
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
