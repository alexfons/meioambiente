(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OrgaoemissorAmbientalDialogController', OrgaoemissorAmbientalDialogController);

    OrgaoemissorAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Orgaoemissor'];

    function OrgaoemissorAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Orgaoemissor) {
        var vm = this;

        vm.orgaoemissor = entity;
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
            if (vm.orgaoemissor.id !== null) {
                Orgaoemissor.update(vm.orgaoemissor, onSaveSuccess, onSaveError);
            } else {
                Orgaoemissor.save(vm.orgaoemissor, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:orgaoemissorUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
