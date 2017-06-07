(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('DesapropriacaoAmbientalDialogController', DesapropriacaoAmbientalDialogController);

    DesapropriacaoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Desapropriacao', 'Planocontas', 'Fonte', 'Categoriainversao', 'Contabancaria', 'Referencia', 'Rodovia'];

    function DesapropriacaoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Desapropriacao, Planocontas, Fonte, Categoriainversao, Contabancaria, Referencia, Rodovia) {
        var vm = this;

        vm.desapropriacao = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.planocontas = Planocontas.query();
        vm.fontes = Fonte.query();
        vm.categoriainversaos = Categoriainversao.query();
        vm.contabancarias = Contabancaria.query();
        vm.referencias = Referencia.query();
        vm.rodovias = Rodovia.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.desapropriacao.id !== null) {
                Desapropriacao.update(vm.desapropriacao, onSaveSuccess, onSaveError);
            } else {
                Desapropriacao.save(vm.desapropriacao, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:desapropriacaoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dataop = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
