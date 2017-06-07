(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ColunaAmbientalDialogController', ColunaAmbientalDialogController);

    ColunaAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Coluna', 'Linha', 'Tabela'];

    function ColunaAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Coluna, Linha, Tabela) {
        var vm = this;

        vm.coluna = entity;
        vm.clear = clear;
        vm.save = save;
        vm.linhas = Linha.query();
        vm.tabelas = Tabela.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.coluna.id !== null) {
                Coluna.update(vm.coluna, onSaveSuccess, onSaveError);
            } else {
                Coluna.save(vm.coluna, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:colunaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
