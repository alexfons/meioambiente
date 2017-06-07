(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciacertificadoirregularidadeAmbientalDialogController', OcorrenciacertificadoirregularidadeAmbientalDialogController);

    OcorrenciacertificadoirregularidadeAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Ocorrenciacertificadoirregularidade', 'Ocorrencia'];

    function OcorrenciacertificadoirregularidadeAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Ocorrenciacertificadoirregularidade, Ocorrencia) {
        var vm = this;

        vm.ocorrenciacertificadoirregularidade = entity;
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
            if (vm.ocorrenciacertificadoirregularidade.id !== null) {
                Ocorrenciacertificadoirregularidade.update(vm.ocorrenciacertificadoirregularidade, onSaveSuccess, onSaveError);
            } else {
                Ocorrenciacertificadoirregularidade.save(vm.ocorrenciacertificadoirregularidade, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:ocorrenciacertificadoirregularidadeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
