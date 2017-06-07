(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ReferenciaAmbientalDialogController', ReferenciaAmbientalDialogController);

    ReferenciaAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Referencia'];

    function ReferenciaAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Referencia) {
        var vm = this;

        vm.referencia = entity;
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
            if (vm.referencia.id !== null) {
                Referencia.update(vm.referencia, onSaveSuccess, onSaveError);
            } else {
                Referencia.save(vm.referencia, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:referenciaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
