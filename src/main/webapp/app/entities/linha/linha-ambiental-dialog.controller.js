(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('LinhaAmbientalDialogController', LinhaAmbientalDialogController);

    LinhaAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Linha', 'Coluna'];

    function LinhaAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Linha, Coluna) {
        var vm = this;

        vm.linha = entity;
        vm.clear = clear;
        vm.save = save;
        vm.colunas = Coluna.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.linha.id !== null) {
                Linha.update(vm.linha, onSaveSuccess, onSaveError);
            } else {
                Linha.save(vm.linha, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:linhaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
