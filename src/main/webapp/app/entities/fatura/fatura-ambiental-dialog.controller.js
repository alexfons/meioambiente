(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FaturaAmbientalDialogController', FaturaAmbientalDialogController);

    FaturaAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Fatura', 'Fonte', 'Contabancaria', 'Contrato', 'Referencia', 'Medicao'];

    function FaturaAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Fatura, Fonte, Contabancaria, Contrato, Referencia, Medicao) {
        var vm = this;

        vm.fatura = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.fontes = Fonte.query();
        vm.contabancarias = Contabancaria.query();
        vm.contratoes = Contrato.query();
        vm.referencias = Referencia.query();
        vm.medicaos = Medicao.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.fatura.id !== null) {
                Fatura.update(vm.fatura, onSaveSuccess, onSaveError);
            } else {
                Fatura.save(vm.fatura, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:faturaUpdate', result);
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
