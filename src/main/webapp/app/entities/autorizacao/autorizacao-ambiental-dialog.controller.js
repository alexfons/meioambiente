(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AutorizacaoAmbientalDialogController', AutorizacaoAmbientalDialogController);

    AutorizacaoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Autorizacao', 'Atividadelicenca', 'Empresa', 'Obra', 'Orgaoemissor', 'Projeto', 'Tipoautorizacao', 'Foto', 'Documento'];

    function AutorizacaoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Autorizacao, Atividadelicenca, Empresa, Obra, Orgaoemissor, Projeto, Tipoautorizacao, Foto, Documento) {
        var vm = this;

        vm.autorizacao = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.atividadelicencas = Atividadelicenca.query();
        vm.empresas = Empresa.query();
        vm.obras = Obra.query();
        vm.orgaoemissors = Orgaoemissor.query();
        vm.projetos = Projeto.query();
        vm.tipoautorizacaos = Tipoautorizacao.query();
        vm.fotos = Foto.query();
        vm.documentos = Documento.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.autorizacao.id !== null) {
                Autorizacao.update(vm.autorizacao, onSaveSuccess, onSaveError);
            } else {
                Autorizacao.save(vm.autorizacao, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:autorizacaoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.data = false;
        vm.datePickerOpenStatus.dataentregadocs = false;
        vm.datePickerOpenStatus.dataexpedicaoprorrogacao1 = false;
        vm.datePickerOpenStatus.dataexpedicaoprorrogacao2 = false;
        vm.datePickerOpenStatus.dataexpedicaoprorrogacao3 = false;
        vm.datePickerOpenStatus.datapedidoprorrogacao1 = false;
        vm.datePickerOpenStatus.datapedidoprorrogacao2 = false;
        vm.datePickerOpenStatus.datapedidoprorrogacao3 = false;
        vm.datePickerOpenStatus.datavalidadeprorrogacao1 = false;
        vm.datePickerOpenStatus.datavalidadeprorrogacao2 = false;
        vm.datePickerOpenStatus.datavalidadeprorrogacao3 = false;
        vm.datePickerOpenStatus.datavencimento = false;
        vm.datePickerOpenStatus.datavencimentoatual = false;
        vm.datePickerOpenStatus.fceidatapagamento = false;
        vm.datePickerOpenStatus.fceidataprotocolo = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
