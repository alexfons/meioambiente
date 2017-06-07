(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FisicoAmbientalDialogController', FisicoAmbientalDialogController);

    FisicoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Fisico', 'Obra', 'Obraatividade'];

    function FisicoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Fisico, Obra, Obraatividade) {
        var vm = this;

        vm.fisico = entity;
        vm.clear = clear;
        vm.save = save;
        vm.obras = Obra.query();
        vm.obraatividades = Obraatividade.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.fisico.id !== null) {
                Fisico.update(vm.fisico, onSaveSuccess, onSaveError);
            } else {
                Fisico.save(vm.fisico, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:fisicoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
