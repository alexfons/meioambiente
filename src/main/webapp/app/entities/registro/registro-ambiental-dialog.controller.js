(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('RegistroAmbientalDialogController', RegistroAmbientalDialogController);

    RegistroAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Registro', 'Linha'];

    function RegistroAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Registro, Linha) {
        var vm = this;

        vm.registro = entity;
        vm.clear = clear;
        vm.save = save;
        vm.linhas = Linha.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.registro.id !== null) {
                Registro.update(vm.registro, onSaveSuccess, onSaveError);
            } else {
                Registro.save(vm.registro, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:registroUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
