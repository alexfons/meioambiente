(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('EditalAmbientalDialogController', EditalAmbientalDialogController);

    EditalAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Edital'];

    function EditalAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Edital) {
        var vm = this;

        vm.edital = entity;
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
            if (vm.edital.id !== null) {
                Edital.update(vm.edital, onSaveSuccess, onSaveError);
            } else {
                Edital.save(vm.edital, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:editalUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dataaprovacao = false;
        vm.datePickerOpenStatus.dataenvio = false;
        vm.datePickerOpenStatus.datahoraabertura = false;
        vm.datePickerOpenStatus.datalicitacao = false;
        vm.datePickerOpenStatus.datanumeromanifestacao = false;
        vm.datePickerOpenStatus.datapublicacao = false;
        vm.datePickerOpenStatus.datarelatorio = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
