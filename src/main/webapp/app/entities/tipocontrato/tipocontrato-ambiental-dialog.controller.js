(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipocontratoAmbientalDialogController', TipocontratoAmbientalDialogController);

    TipocontratoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Tipocontrato'];

    function TipocontratoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Tipocontrato) {
        var vm = this;

        vm.tipocontrato = entity;
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
            if (vm.tipocontrato.id !== null) {
                Tipocontrato.update(vm.tipocontrato, onSaveSuccess, onSaveError);
            } else {
                Tipocontrato.save(vm.tipocontrato, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:tipocontratoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
