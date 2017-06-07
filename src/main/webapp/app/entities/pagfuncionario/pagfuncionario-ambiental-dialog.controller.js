(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('PagfuncionarioAmbientalDialogController', PagfuncionarioAmbientalDialogController);

    PagfuncionarioAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Pagfuncionario', 'Planocontas', 'Fonte', 'Categoriainversao', 'Funcionario', 'Referencia'];

    function PagfuncionarioAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Pagfuncionario, Planocontas, Fonte, Categoriainversao, Funcionario, Referencia) {
        var vm = this;

        vm.pagfuncionario = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.planocontas = Planocontas.query();
        vm.fontes = Fonte.query();
        vm.categoriainversaos = Categoriainversao.query();
        vm.funcionarios = Funcionario.query();
        vm.referencias = Referencia.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.pagfuncionario.id !== null) {
                Pagfuncionario.update(vm.pagfuncionario, onSaveSuccess, onSaveError);
            } else {
                Pagfuncionario.save(vm.pagfuncionario, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:pagfuncionarioUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.datapagamento = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
