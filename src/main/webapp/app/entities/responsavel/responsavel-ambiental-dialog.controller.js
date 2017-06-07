(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ResponsavelAmbientalDialogController', ResponsavelAmbientalDialogController);

    ResponsavelAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Responsavel'];

    function ResponsavelAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Responsavel) {
        var vm = this;

        vm.responsavel = entity;
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
            if (vm.responsavel.id !== null) {
                Responsavel.update(vm.responsavel, onSaveSuccess, onSaveError);
            } else {
                Responsavel.save(vm.responsavel, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:responsavelUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
