(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciapendenciasAmbientalDialogController', OcorrenciapendenciasAmbientalDialogController);

    OcorrenciapendenciasAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Ocorrenciapendencias', 'Ocorrencia'];

    function OcorrenciapendenciasAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Ocorrenciapendencias, Ocorrencia) {
        var vm = this;

        vm.ocorrenciapendencias = entity;
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
            if (vm.ocorrenciapendencias.id !== null) {
                Ocorrenciapendencias.update(vm.ocorrenciapendencias, onSaveSuccess, onSaveError);
            } else {
                Ocorrenciapendencias.save(vm.ocorrenciapendencias, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:ocorrenciapendenciasUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
