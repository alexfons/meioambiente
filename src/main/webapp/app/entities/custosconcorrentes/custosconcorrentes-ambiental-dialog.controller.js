(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CustosconcorrentesAmbientalDialogController', CustosconcorrentesAmbientalDialogController);

    CustosconcorrentesAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Custosconcorrentes', 'Planocontas', 'Fonte', 'Categoriainversao'];

    function CustosconcorrentesAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Custosconcorrentes, Planocontas, Fonte, Categoriainversao) {
        var vm = this;

        vm.custosconcorrentes = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.planocontas = Planocontas.query();
        vm.fontes = Fonte.query();
        vm.categoriainversaos = Categoriainversao.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.custosconcorrentes.id !== null) {
                Custosconcorrentes.update(vm.custosconcorrentes, onSaveSuccess, onSaveError);
            } else {
                Custosconcorrentes.save(vm.custosconcorrentes, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:custosconcorrentesUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.datainicio = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
