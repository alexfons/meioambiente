(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipocertificadoconformidadeAmbientalDialogController', TipocertificadoconformidadeAmbientalDialogController);

    TipocertificadoconformidadeAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Tipocertificadoconformidade'];

    function TipocertificadoconformidadeAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Tipocertificadoconformidade) {
        var vm = this;

        vm.tipocertificadoconformidade = entity;
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
            if (vm.tipocertificadoconformidade.id !== null) {
                Tipocertificadoconformidade.update(vm.tipocertificadoconformidade, onSaveSuccess, onSaveError);
            } else {
                Tipocertificadoconformidade.save(vm.tipocertificadoconformidade, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:tipocertificadoconformidadeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
