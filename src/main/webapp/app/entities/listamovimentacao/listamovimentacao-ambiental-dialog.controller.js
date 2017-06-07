(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ListamovimentacaoAmbientalDialogController', ListamovimentacaoAmbientalDialogController);

    ListamovimentacaoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Listamovimentacao', 'Planocontas', 'Movimentacao'];

    function ListamovimentacaoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Listamovimentacao, Planocontas, Movimentacao) {
        var vm = this;

        vm.listamovimentacao = entity;
        vm.clear = clear;
        vm.save = save;
        vm.planocontas = Planocontas.query();
        vm.movimentacaos = Movimentacao.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.listamovimentacao.id !== null) {
                Listamovimentacao.update(vm.listamovimentacao, onSaveSuccess, onSaveError);
            } else {
                Listamovimentacao.save(vm.listamovimentacao, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:listamovimentacaoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
