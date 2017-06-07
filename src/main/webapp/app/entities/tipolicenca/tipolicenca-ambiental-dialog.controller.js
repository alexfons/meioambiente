(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipolicencaAmbientalDialogController', TipolicencaAmbientalDialogController);

    TipolicencaAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Tipolicenca'];

    function TipolicencaAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Tipolicenca) {
        var vm = this;

        vm.tipolicenca = entity;
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
            if (vm.tipolicenca.id !== null) {
                Tipolicenca.update(vm.tipolicenca, onSaveSuccess, onSaveError);
            } else {
                Tipolicenca.save(vm.tipolicenca, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:tipolicencaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
