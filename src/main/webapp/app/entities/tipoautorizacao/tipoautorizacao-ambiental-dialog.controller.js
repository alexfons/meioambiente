(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoautorizacaoAmbientalDialogController', TipoautorizacaoAmbientalDialogController);

    TipoautorizacaoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Tipoautorizacao'];

    function TipoautorizacaoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Tipoautorizacao) {
        var vm = this;

        vm.tipoautorizacao = entity;
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
            if (vm.tipoautorizacao.id !== null) {
                Tipoautorizacao.update(vm.tipoautorizacao, onSaveSuccess, onSaveError);
            } else {
                Tipoautorizacao.save(vm.tipoautorizacao, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:tipoautorizacaoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
