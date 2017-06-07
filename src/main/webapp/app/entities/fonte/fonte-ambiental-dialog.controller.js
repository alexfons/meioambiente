(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FonteAmbientalDialogController', FonteAmbientalDialogController);

    FonteAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Fonte', 'Contabancaria'];

    function FonteAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Fonte, Contabancaria) {
        var vm = this;

        vm.fonte = entity;
        vm.clear = clear;
        vm.save = save;
        vm.contabancarias = Contabancaria.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.fonte.id !== null) {
                Fonte.update(vm.fonte, onSaveSuccess, onSaveError);
            } else {
                Fonte.save(vm.fonte, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:fonteUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
