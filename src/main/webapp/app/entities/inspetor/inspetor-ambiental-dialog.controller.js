(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('InspetorAmbientalDialogController', InspetorAmbientalDialogController);

    InspetorAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Inspetor'];

    function InspetorAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Inspetor) {
        var vm = this;

        vm.inspetor = entity;
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
            if (vm.inspetor.id !== null) {
                Inspetor.update(vm.inspetor, onSaveSuccess, onSaveError);
            } else {
                Inspetor.save(vm.inspetor, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:inspetorUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
