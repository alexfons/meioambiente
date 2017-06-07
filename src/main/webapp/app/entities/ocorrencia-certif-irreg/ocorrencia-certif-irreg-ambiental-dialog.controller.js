(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciaCertifIrregAmbientalDialogController', OcorrenciaCertifIrregAmbientalDialogController);

    OcorrenciaCertifIrregAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'OcorrenciaCertifIrreg', 'Ocorrencia'];

    function OcorrenciaCertifIrregAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, OcorrenciaCertifIrreg, Ocorrencia) {
        var vm = this;

        vm.ocorrenciaCertifIrreg = entity;
        vm.clear = clear;
        vm.save = save;
        vm.ocorrencias = Ocorrencia.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.ocorrenciaCertifIrreg.id !== null) {
                OcorrenciaCertifIrreg.update(vm.ocorrenciaCertifIrreg, onSaveSuccess, onSaveError);
            } else {
                OcorrenciaCertifIrreg.save(vm.ocorrenciaCertifIrreg, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:ocorrenciaCertifIrregUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
