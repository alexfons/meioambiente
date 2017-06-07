(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ClausulascontratuaisAmbientalDialogController', ClausulascontratuaisAmbientalDialogController);

    ClausulascontratuaisAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Clausulascontratuais', 'Contratoagente'];

    function ClausulascontratuaisAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Clausulascontratuais, Contratoagente) {
        var vm = this;

        vm.clausulascontratuais = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.contratoagentes = Contratoagente.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.clausulascontratuais.id !== null) {
                Clausulascontratuais.update(vm.clausulascontratuais, onSaveSuccess, onSaveError);
            } else {
                Clausulascontratuais.save(vm.clausulascontratuais, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:clausulascontratuaisUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dataaprovacao = false;
        vm.datePickerOpenStatus.dataenvio = false;
        vm.datePickerOpenStatus.datavigente = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
