(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoobraAmbientalDialogController', TipoobraAmbientalDialogController);

    TipoobraAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Tipoobra'];

    function TipoobraAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Tipoobra) {
        var vm = this;

        vm.tipoobra = entity;
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
            if (vm.tipoobra.id !== null) {
                Tipoobra.update(vm.tipoobra, onSaveSuccess, onSaveError);
            } else {
                Tipoobra.save(vm.tipoobra, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:tipoobraUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
