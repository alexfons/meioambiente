(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CoordenadaAmbientalDialogController', CoordenadaAmbientalDialogController);

    CoordenadaAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Coordenada'];

    function CoordenadaAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Coordenada) {
        var vm = this;

        vm.coordenada = entity;
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
            if (vm.coordenada.id !== null) {
                Coordenada.update(vm.coordenada, onSaveSuccess, onSaveError);
            } else {
                Coordenada.save(vm.coordenada, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:coordenadaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
