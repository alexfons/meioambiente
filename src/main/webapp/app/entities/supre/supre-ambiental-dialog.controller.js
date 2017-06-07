(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('SupreAmbientalDialogController', SupreAmbientalDialogController);

    SupreAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Supre'];

    function SupreAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Supre) {
        var vm = this;

        vm.supre = entity;
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
            if (vm.supre.id !== null) {
                Supre.update(vm.supre, onSaveSuccess, onSaveError);
            } else {
                Supre.save(vm.supre, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:supreUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
