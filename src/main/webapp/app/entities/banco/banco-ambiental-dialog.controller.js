(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('BancoAmbientalDialogController', BancoAmbientalDialogController);

    BancoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Banco'];

    function BancoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Banco) {
        var vm = this;

        vm.banco = entity;
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
            if (vm.banco.id !== null) {
                Banco.update(vm.banco, onSaveSuccess, onSaveError);
            } else {
                Banco.save(vm.banco, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:bancoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
