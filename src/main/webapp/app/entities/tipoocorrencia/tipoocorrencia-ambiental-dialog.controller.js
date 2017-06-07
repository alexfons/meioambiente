(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoocorrenciaAmbientalDialogController', TipoocorrenciaAmbientalDialogController);

    TipoocorrenciaAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Tipoocorrencia'];

    function TipoocorrenciaAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Tipoocorrencia) {
        var vm = this;

        vm.tipoocorrencia = entity;
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
            if (vm.tipoocorrencia.id !== null) {
                Tipoocorrencia.update(vm.tipoocorrencia, onSaveSuccess, onSaveError);
            } else {
                Tipoocorrencia.save(vm.tipoocorrencia, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:tipoocorrenciaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
