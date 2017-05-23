(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FotoAmbientalDialogController', FotoAmbientalDialogController);

    FotoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Foto'];

    function FotoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Foto) {
        var vm = this;

        vm.foto = entity;
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
            if (vm.foto.id !== null) {
                Foto.update(vm.foto, onSaveSuccess, onSaveError);
            } else {
                Foto.save(vm.foto, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:fotoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
