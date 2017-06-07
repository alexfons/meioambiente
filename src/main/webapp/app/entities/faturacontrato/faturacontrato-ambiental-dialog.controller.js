(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FaturacontratoAmbientalDialogController', FaturacontratoAmbientalDialogController);

    FaturacontratoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Faturacontrato', 'Fonte', 'Contrato', 'Referenciacontrato'];

    function FaturacontratoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Faturacontrato, Fonte, Contrato, Referenciacontrato) {
        var vm = this;

        vm.faturacontrato = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.fontes = Fonte.query();
        vm.contratoes = Contrato.query();
        vm.referenciacontratoes = Referenciacontrato.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.faturacontrato.id !== null) {
                Faturacontrato.update(vm.faturacontrato, onSaveSuccess, onSaveError);
            } else {
                Faturacontrato.save(vm.faturacontrato, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:faturacontratoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dataop = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
