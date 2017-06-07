(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ContratoAmbientalDialogController', ContratoAmbientalDialogController);

    ContratoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Contrato', 'Empresa', 'Planocontas', 'Natureza', 'Proposta', 'Categoriainversao', 'Aditivocontrato', 'Contratoobra', 'Empresacontrato'];

    function ContratoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Contrato, Empresa, Planocontas, Natureza, Proposta, Categoriainversao, Aditivocontrato, Contratoobra, Empresacontrato) {
        var vm = this;

        vm.contrato = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.idempresas = Empresa.query({filter: 'contrato-is-null'});
        $q.all([vm.contrato.$promise, vm.idempresas.$promise]).then(function() {
            if (!vm.contrato.idempresaId) {
                return $q.reject();
            }
            return Empresa.get({id : vm.contrato.idempresaId}).$promise;
        }).then(function(idempresa) {
            vm.idempresas.push(idempresa);
        });
        vm.planocontas = Planocontas.query();
        vm.naturezas = Natureza.query();
        vm.propostas = Proposta.query();
        vm.categoriainversaos = Categoriainversao.query();
        vm.aditivocontratoes = Aditivocontrato.query();
        vm.contratoobras = Contratoobra.query();
        vm.empresacontratoes = Empresacontrato.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.contrato.id !== null) {
                Contrato.update(vm.contrato, onSaveSuccess, onSaveError);
            } else {
                Contrato.save(vm.contrato, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:contratoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dataatual = false;
        vm.datePickerOpenStatus.databasecontrato = false;
        vm.datePickerOpenStatus.datacontratacao = false;
        vm.datePickerOpenStatus.dataterminocaucao = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
