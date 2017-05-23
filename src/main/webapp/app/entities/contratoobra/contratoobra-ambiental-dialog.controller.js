(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ContratoobraAmbientalDialogController', ContratoobraAmbientalDialogController);

    ContratoobraAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Contratoobra'];

    function ContratoobraAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Contratoobra) {
        var vm = this;

        vm.contratoobra = entity;
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
            if (vm.contratoobra.id !== null) {
                Contratoobra.update(vm.contratoobra, onSaveSuccess, onSaveError);
            } else {
                Contratoobra.save(vm.contratoobra, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:contratoobraUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
