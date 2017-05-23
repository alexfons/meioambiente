(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoObraAmbientalDialogController', TipoObraAmbientalDialogController);

    TipoObraAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'TipoObra'];

    function TipoObraAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, TipoObra) {
        var vm = this;

        vm.tipoObra = entity;
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
            if (vm.tipoObra.id !== null) {
                TipoObra.update(vm.tipoObra, onSaveSuccess, onSaveError);
            } else {
                TipoObra.save(vm.tipoObra, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:tipoObraUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
