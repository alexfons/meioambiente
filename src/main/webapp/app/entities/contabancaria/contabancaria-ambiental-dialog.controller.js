(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ContabancariaAmbientalDialogController', ContabancariaAmbientalDialogController);

    ContabancariaAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Contabancaria', 'Planocontas'];

    function ContabancariaAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Contabancaria, Planocontas) {
        var vm = this;

        vm.contabancaria = entity;
        vm.clear = clear;
        vm.save = save;
        vm.planocontas = Planocontas.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.contabancaria.id !== null) {
                Contabancaria.update(vm.contabancaria, onSaveSuccess, onSaveError);
            } else {
                Contabancaria.save(vm.contabancaria, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:contabancariaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
