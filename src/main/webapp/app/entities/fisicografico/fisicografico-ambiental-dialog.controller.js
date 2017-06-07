(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FisicograficoAmbientalDialogController', FisicograficoAmbientalDialogController);

    FisicograficoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Fisicografico', 'Atividade'];

    function FisicograficoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Fisicografico, Atividade) {
        var vm = this;

        vm.fisicografico = entity;
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
            if (vm.fisicografico.id !== null) {
                Fisicografico.update(vm.fisicografico, onSaveSuccess, onSaveError);
            } else {
                Fisicografico.save(vm.fisicografico, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:fisicograficoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
