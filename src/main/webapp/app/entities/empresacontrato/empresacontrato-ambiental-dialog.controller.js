(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('EmpresacontratoAmbientalDialogController', EmpresacontratoAmbientalDialogController);

    EmpresacontratoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Empresacontrato'];

    function EmpresacontratoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Empresacontrato) {
        var vm = this;

        vm.empresacontrato = entity;
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
            if (vm.empresacontrato.id !== null) {
                Empresacontrato.update(vm.empresacontrato, onSaveSuccess, onSaveError);
            } else {
                Empresacontrato.save(vm.empresacontrato, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:empresacontratoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
