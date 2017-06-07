(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('LancamentosAmbientalDialogController', LancamentosAmbientalDialogController);

    LancamentosAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Lancamentos', 'Fonte', 'Planocontas'];

    function LancamentosAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Lancamentos, Fonte, Planocontas) {
        var vm = this;

        vm.lancamentos = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.fontes = Fonte.query();
        vm.planocontas = Planocontas.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.lancamentos.id !== null) {
                Lancamentos.update(vm.lancamentos, onSaveSuccess, onSaveError);
            } else {
                Lancamentos.save(vm.lancamentos, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:lancamentosUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.datalan = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
