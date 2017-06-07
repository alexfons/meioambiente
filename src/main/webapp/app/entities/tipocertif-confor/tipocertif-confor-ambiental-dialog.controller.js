(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipocertifConforAmbientalDialogController', TipocertifConforAmbientalDialogController);

    TipocertifConforAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'TipocertifConfor'];

    function TipocertifConforAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, TipocertifConfor) {
        var vm = this;

        vm.tipocertifConfor = entity;
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
            if (vm.tipocertifConfor.id !== null) {
                TipocertifConfor.update(vm.tipocertifConfor, onSaveSuccess, onSaveError);
            } else {
                TipocertifConfor.save(vm.tipocertifConfor, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:tipocertifConforUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
