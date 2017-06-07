(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('PlanoaquisicoesAmbientalDialogController', PlanoaquisicoesAmbientalDialogController);

    PlanoaquisicoesAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Planoaquisicoes', 'Fonte', 'Contratoagente', 'Edital'];

    function PlanoaquisicoesAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Planoaquisicoes, Fonte, Contratoagente, Edital) {
        var vm = this;

        vm.planoaquisicoes = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.fontes = Fonte.query();
        vm.contratoagentes = Contratoagente.query();
        vm.editals = Edital.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.planoaquisicoes.id !== null) {
                Planoaquisicoes.update(vm.planoaquisicoes, onSaveSuccess, onSaveError);
            } else {
                Planoaquisicoes.save(vm.planoaquisicoes, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:planoaquisicoesUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.avisolicitacao = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
