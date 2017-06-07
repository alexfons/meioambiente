(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TabelaAmbientalDialogController', TabelaAmbientalDialogController);

    TabelaAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Tabela'];

    function TabelaAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Tabela) {
        var vm = this;

        vm.tabela = entity;
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
            if (vm.tabela.id !== null) {
                Tabela.update(vm.tabela, onSaveSuccess, onSaveError);
            } else {
                Tabela.save(vm.tabela, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:tabelaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
