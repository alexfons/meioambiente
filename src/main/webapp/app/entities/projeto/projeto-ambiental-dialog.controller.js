(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ProjetoAmbientalDialogController', ProjetoAmbientalDialogController);

    ProjetoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Projeto', 'Inspetor', 'Municipio', 'Tipoobra', 'Trecho', 'Fiscal', 'Contratoprojeto', 'Historico'];

    function ProjetoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Projeto, Inspetor, Municipio, Tipoobra, Trecho, Fiscal, Contratoprojeto, Historico) {
        var vm = this;

        vm.projeto = entity;
        vm.clear = clear;
        vm.save = save;
        vm.inspetors = Inspetor.query();
        vm.municipios = Municipio.query();
        vm.tipoobras = Tipoobra.query();
        vm.trechos = Trecho.query();
        vm.fiscals = Fiscal.query();
        vm.contratoprojetos = Contratoprojeto.query();
        vm.historicos = Historico.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.projeto.id !== null) {
                Projeto.update(vm.projeto, onSaveSuccess, onSaveError);
            } else {
                Projeto.save(vm.projeto, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:projetoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
