(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ManifestacaoAmbientalDialogController', ManifestacaoAmbientalDialogController);

    ManifestacaoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Manifestacao'];

    function ManifestacaoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Manifestacao) {
        var vm = this;

        vm.manifestacao = entity;
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
            if (vm.manifestacao.id !== null) {
                Manifestacao.update(vm.manifestacao, onSaveSuccess, onSaveError);
            } else {
                Manifestacao.save(vm.manifestacao, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:manifestacaoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dataaviso = false;
        vm.datePickerOpenStatus.dataentrega = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
