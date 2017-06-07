(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TrechoAmbientalDialogController', TrechoAmbientalDialogController);

    TrechoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Trecho', 'Rodovia', 'Supre'];

    function TrechoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Trecho, Rodovia, Supre) {
        var vm = this;

        vm.trecho = entity;
        vm.clear = clear;
        vm.save = save;
        vm.rodovias = Rodovia.query();
        vm.supres = Supre.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.trecho.id !== null) {
                Trecho.update(vm.trecho, onSaveSuccess, onSaveError);
            } else {
                Trecho.save(vm.trecho, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:trechoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
