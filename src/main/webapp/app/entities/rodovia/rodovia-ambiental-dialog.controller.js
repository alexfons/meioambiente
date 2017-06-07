(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('RodoviaAmbientalDialogController', RodoviaAmbientalDialogController);

    RodoviaAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Rodovia'];

    function RodoviaAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Rodovia) {
        var vm = this;

        vm.rodovia = entity;
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
            if (vm.rodovia.id !== null) {
                Rodovia.update(vm.rodovia, onSaveSuccess, onSaveError);
            } else {
                Rodovia.save(vm.rodovia, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:rodoviaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
