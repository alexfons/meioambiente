(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('InformeCertifIrregAmbientalDialogController', InformeCertifIrregAmbientalDialogController);

    InformeCertifIrregAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'InformeCertifIrreg', 'Informe'];

    function InformeCertifIrregAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, InformeCertifIrreg, Informe) {
        var vm = this;

        vm.informeCertifIrreg = entity;
        vm.clear = clear;
        vm.save = save;
        vm.informes = Informe.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.informeCertifIrreg.id !== null) {
                InformeCertifIrreg.update(vm.informeCertifIrreg, onSaveSuccess, onSaveError);
            } else {
                InformeCertifIrreg.save(vm.informeCertifIrreg, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:informeCertifIrregUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
