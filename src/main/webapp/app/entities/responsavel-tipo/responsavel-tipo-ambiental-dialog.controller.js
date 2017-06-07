(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ResponsavelTipoAmbientalDialogController', ResponsavelTipoAmbientalDialogController);

    ResponsavelTipoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ResponsavelTipo'];

    function ResponsavelTipoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ResponsavelTipo) {
        var vm = this;

        vm.responsavelTipo = entity;
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
            if (vm.responsavelTipo.id !== null) {
                ResponsavelTipo.update(vm.responsavelTipo, onSaveSuccess, onSaveError);
            } else {
                ResponsavelTipo.save(vm.responsavelTipo, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:responsavelTipoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
