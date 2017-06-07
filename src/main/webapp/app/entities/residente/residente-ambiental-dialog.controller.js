(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ResidenteAmbientalDialogController', ResidenteAmbientalDialogController);

    ResidenteAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Residente'];

    function ResidenteAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Residente) {
        var vm = this;

        vm.residente = entity;
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
            if (vm.residente.id !== null) {
                Residente.update(vm.residente, onSaveSuccess, onSaveError);
            } else {
                Residente.save(vm.residente, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:residenteUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
