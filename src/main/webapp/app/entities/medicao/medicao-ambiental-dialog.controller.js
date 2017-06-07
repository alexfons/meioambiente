(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('MedicaoAmbientalDialogController', MedicaoAmbientalDialogController);

    MedicaoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Medicao', 'Contrato'];

    function MedicaoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Medicao, Contrato) {
        var vm = this;

        vm.medicao = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.contratoes = Contrato.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.medicao.id !== null) {
                Medicao.update(vm.medicao, onSaveSuccess, onSaveError);
            } else {
                Medicao.save(vm.medicao, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:medicaoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.mes = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
