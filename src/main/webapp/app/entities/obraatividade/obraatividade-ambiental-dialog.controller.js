(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ObraatividadeAmbientalDialogController', ObraatividadeAmbientalDialogController);

    ObraatividadeAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Obraatividade', 'Atividade'];

    function ObraatividadeAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Obraatividade, Atividade) {
        var vm = this;

        vm.obraatividade = entity;
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
            if (vm.obraatividade.id !== null) {
                Obraatividade.update(vm.obraatividade, onSaveSuccess, onSaveError);
            } else {
                Obraatividade.save(vm.obraatividade, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:obraatividadeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
