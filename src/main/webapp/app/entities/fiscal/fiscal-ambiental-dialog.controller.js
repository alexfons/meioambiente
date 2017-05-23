(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FiscalAmbientalDialogController', FiscalAmbientalDialogController);

    FiscalAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Fiscal'];

    function FiscalAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Fiscal) {
        var vm = this;

        vm.fiscal = entity;
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
            if (vm.fiscal.id !== null) {
                Fiscal.update(vm.fiscal, onSaveSuccess, onSaveError);
            } else {
                Fiscal.save(vm.fiscal, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:fiscalUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
