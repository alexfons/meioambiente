(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ValoresdesembolsoAmbientalDialogController', ValoresdesembolsoAmbientalDialogController);

    ValoresdesembolsoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Valoresdesembolso', 'Contabancaria', 'Referencia'];

    function ValoresdesembolsoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Valoresdesembolso, Contabancaria, Referencia) {
        var vm = this;

        vm.valoresdesembolso = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.contabancarias = Contabancaria.query();
        vm.referencias = Referencia.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.valoresdesembolso.id !== null) {
                Valoresdesembolso.update(vm.valoresdesembolso, onSaveSuccess, onSaveError);
            } else {
                Valoresdesembolso.save(vm.valoresdesembolso, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:valoresdesembolsoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.datainternalizacao = false;
        vm.datePickerOpenStatus.valuedata = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
