(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CategoriainversaoAmbientalDialogController', CategoriainversaoAmbientalDialogController);

    CategoriainversaoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Categoriainversao', 'Contratoagente'];

    function CategoriainversaoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Categoriainversao, Contratoagente) {
        var vm = this;

        vm.categoriainversao = entity;
        vm.clear = clear;
        vm.save = save;
        vm.contratoagentes = Contratoagente.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.categoriainversao.id !== null) {
                Categoriainversao.update(vm.categoriainversao, onSaveSuccess, onSaveError);
            } else {
                Categoriainversao.save(vm.categoriainversao, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:categoriainversaoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
