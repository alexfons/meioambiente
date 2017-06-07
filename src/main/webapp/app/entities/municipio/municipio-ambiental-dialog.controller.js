(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('MunicipioAmbientalDialogController', MunicipioAmbientalDialogController);

    MunicipioAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Municipio'];

    function MunicipioAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Municipio) {
        var vm = this;

        vm.municipio = entity;
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
            if (vm.municipio.id !== null) {
                Municipio.update(vm.municipio, onSaveSuccess, onSaveError);
            } else {
                Municipio.save(vm.municipio, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:municipioUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
