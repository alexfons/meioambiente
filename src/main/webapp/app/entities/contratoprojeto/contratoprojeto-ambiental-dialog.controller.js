(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ContratoprojetoAmbientalDialogController', ContratoprojetoAmbientalDialogController);

    ContratoprojetoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Contratoprojeto', 'Contrato', 'Responsavel'];

    function ContratoprojetoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Contratoprojeto, Contrato, Responsavel) {
        var vm = this;

        vm.contratoprojeto = entity;
        vm.clear = clear;
        vm.save = save;
        vm.contratoes = Contrato.query();
        vm.responsavels = Responsavel.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.contratoprojeto.id !== null) {
                Contratoprojeto.update(vm.contratoprojeto, onSaveSuccess, onSaveError);
            } else {
                Contratoprojeto.save(vm.contratoprojeto, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:contratoprojetoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
