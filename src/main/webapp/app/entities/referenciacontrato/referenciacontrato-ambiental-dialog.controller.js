(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ReferenciacontratoAmbientalDialogController', ReferenciacontratoAmbientalDialogController);

    ReferenciacontratoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Referenciacontrato'];

    function ReferenciacontratoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Referenciacontrato) {
        var vm = this;

        vm.referenciacontrato = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.referenciacontrato.id !== null) {
                Referenciacontrato.update(vm.referenciacontrato, onSaveSuccess, onSaveError);
            } else {
                Referenciacontrato.save(vm.referenciacontrato, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:referenciacontratoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
