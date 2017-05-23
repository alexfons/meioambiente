(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ApresentacaoAmbientalDialogController', ApresentacaoAmbientalDialogController);

    ApresentacaoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Apresentacao', 'Obra', 'Ocorrenciaapresentacao', 'Foto'];

    function ApresentacaoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Apresentacao, Obra, Ocorrenciaapresentacao, Foto) {
        var vm = this;

        vm.apresentacao = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.obras = Obra.query();
        vm.ocorrenciaapresentacaos = Ocorrenciaapresentacao.query();
        vm.fotos = Foto.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.apresentacao.id !== null) {
                Apresentacao.update(vm.apresentacao, onSaveSuccess, onSaveError);
            } else {
                Apresentacao.save(vm.apresentacao, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:apresentacaoUpdate', result);
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
