(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ObraAmbientalDialogController', ObraAmbientalDialogController);

    ObraAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Obra', 'TipoObra', 'Inspetor', 'Fiscal', 'Trecho', 'Contratoobra', 'Historico'];

    function ObraAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Obra, TipoObra, Inspetor, Fiscal, Trecho, Contratoobra, Historico) {
        var vm = this;

        vm.obra = entity;
        vm.clear = clear;
        vm.save = save;
        vm.tipoobras = TipoObra.query();
        vm.inspetors = Inspetor.query();
        vm.fiscals = Fiscal.query();
        vm.trechos = Trecho.query();
        vm.contratoobras = Contratoobra.query();
        vm.historicos = Historico.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.obra.id !== null) {
                Obra.update(vm.obra, onSaveSuccess, onSaveError);
            } else {
                Obra.save(vm.obra, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:obraUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
