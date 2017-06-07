(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('InformecertificadoirregularidadeAmbientalDialogController', InformecertificadoirregularidadeAmbientalDialogController);

    InformecertificadoirregularidadeAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Informecertificadoirregularidade'];

    function InformecertificadoirregularidadeAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Informecertificadoirregularidade) {
        var vm = this;

        vm.informecertificadoirregularidade = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.informecertificadoirregularidade.id !== null) {
                Informecertificadoirregularidade.update(vm.informecertificadoirregularidade, onSaveSuccess, onSaveError);
            } else {
                Informecertificadoirregularidade.save(vm.informecertificadoirregularidade, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:informecertificadoirregularidadeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
