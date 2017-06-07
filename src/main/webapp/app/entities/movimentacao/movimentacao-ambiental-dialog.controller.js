(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('MovimentacaoAmbientalDialogController', MovimentacaoAmbientalDialogController);

    MovimentacaoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Movimentacao', 'Listamovimentacao', 'Contabancaria', 'Fonte'];

    function MovimentacaoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Movimentacao, Listamovimentacao, Contabancaria, Fonte) {
        var vm = this;

        vm.movimentacao = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.listamovimentacaos = Listamovimentacao.query();
        vm.contabancarias = Contabancaria.query();
        vm.fontes = Fonte.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.movimentacao.id !== null) {
                Movimentacao.update(vm.movimentacao, onSaveSuccess, onSaveError);
            } else {
                Movimentacao.save(vm.movimentacao, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:movimentacaoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.data = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
