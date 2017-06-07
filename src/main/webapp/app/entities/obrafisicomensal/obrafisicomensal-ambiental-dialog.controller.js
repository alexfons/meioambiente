(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ObrafisicomensalAmbientalDialogController', ObrafisicomensalAmbientalDialogController);

    ObrafisicomensalAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Obrafisicomensal', 'Fisico', 'Atividadeexecutadamensal'];

    function ObrafisicomensalAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Obrafisicomensal, Fisico, Atividadeexecutadamensal) {
        var vm = this;

        vm.obrafisicomensal = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.fisicos = Fisico.query();
        vm.atividadeexecutadamensals = Atividadeexecutadamensal.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.obrafisicomensal.id !== null) {
                Obrafisicomensal.update(vm.obrafisicomensal, onSaveSuccess, onSaveError);
            } else {
                Obrafisicomensal.save(vm.obrafisicomensal, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:obrafisicomensalUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.datainspecao = false;
        vm.datePickerOpenStatus.datarelatorio = false;
        vm.datePickerOpenStatus.previsaoatual = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
