(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AtividadeexecutadamensalAmbientalDialogController', AtividadeexecutadamensalAmbientalDialogController);

    AtividadeexecutadamensalAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Atividadeexecutadamensal', 'Atividade'];

    function AtividadeexecutadamensalAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Atividadeexecutadamensal, Atividade) {
        var vm = this;

        vm.atividadeexecutadamensal = entity;
        vm.clear = clear;
        vm.save = save;
        vm.atividades = Atividade.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.atividadeexecutadamensal.id !== null) {
                Atividadeexecutadamensal.update(vm.atividadeexecutadamensal, onSaveSuccess, onSaveError);
            } else {
                Atividadeexecutadamensal.save(vm.atividadeexecutadamensal, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:atividadeexecutadamensalUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
