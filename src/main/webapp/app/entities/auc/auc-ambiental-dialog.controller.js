(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AucAmbientalDialogController', AucAmbientalDialogController);

    AucAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Auc', 'Atividadelicenca', 'Orgaoemissor', 'Obra', 'Projeto', 'Empresa', 'Condicionante', 'Foto', 'Documento'];

    function AucAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Auc, Atividadelicenca, Orgaoemissor, Obra, Projeto, Empresa, Condicionante, Foto, Documento) {
        var vm = this;

        vm.auc = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.atividadelicencas = Atividadelicenca.query();
        vm.orgaoemissors = Orgaoemissor.query();
        vm.obras = Obra.query();
        vm.projetos = Projeto.query();
        vm.empresas = Empresa.query();
        vm.condicionantes = Condicionante.query();
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
            if (vm.auc.id !== null) {
                Auc.update(vm.auc, onSaveSuccess, onSaveError);
            } else {
                Auc.save(vm.auc, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:aucUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.fceidataprotocolo = false;
        vm.datePickerOpenStatus.fceidatapagamento = false;
        vm.datePickerOpenStatus.dataentregadocs = false;
        vm.datePickerOpenStatus.dataoficiolocalpedido = false;
        vm.datePickerOpenStatus.dataoficioreoficialpedido = false;
        vm.datePickerOpenStatus.dataoficiolocalrecebimento = false;
        vm.datePickerOpenStatus.dataoficioreoficialrecebimento = false;
        vm.datePickerOpenStatus.dataemissao = false;
        vm.datePickerOpenStatus.datapedidoprorrogacao1 = false;
        vm.datePickerOpenStatus.dataexpedicaoprorrogacao1 = false;
        vm.datePickerOpenStatus.datavalidadeprorrogacao1 = false;
        vm.datePickerOpenStatus.datapedidoprorrogacao2 = false;
        vm.datePickerOpenStatus.dataexpedicaoprorrogacao2 = false;
        vm.datePickerOpenStatus.datavalidadeprorrogacao2 = false;
        vm.datePickerOpenStatus.datapedidoprorrogacao3 = false;
        vm.datePickerOpenStatus.dataexpedicaoprorrogacao3 = false;
        vm.datePickerOpenStatus.datavalidadeprorrogacao3 = false;
        vm.datePickerOpenStatus.datavencimento = false;
        vm.datePickerOpenStatus.datavencimentoatual = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
