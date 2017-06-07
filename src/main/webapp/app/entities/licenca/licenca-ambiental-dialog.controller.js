(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('LicencaAmbientalDialogController', LicencaAmbientalDialogController);

    LicencaAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Licenca', 'Atividadelicenca', 'Empresa', 'Projeto', 'Tipolicenca', 'Obra', 'Orgaoemissor', 'Condicionante', 'Documento', 'Foto'];

    function LicencaAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Licenca, Atividadelicenca, Empresa, Projeto, Tipolicenca, Obra, Orgaoemissor, Condicionante, Documento, Foto) {
        var vm = this;

        vm.licenca = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.atividadelicencas = Atividadelicenca.query();
        vm.empresas = Empresa.query();
        vm.projetos = Projeto.query();
        vm.tipolicencas = Tipolicenca.query();
        vm.obras = Obra.query();
        vm.orgaoemissors = Orgaoemissor.query();
        vm.condicionantes = Condicionante.query();
        vm.documentos = Documento.query();
        vm.fotos = Foto.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.licenca.id !== null) {
                Licenca.update(vm.licenca, onSaveSuccess, onSaveError);
            } else {
                Licenca.save(vm.licenca, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:licencaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dataemissao = false;
        vm.datePickerOpenStatus.dataentregadocs = false;
        vm.datePickerOpenStatus.dataexpedicaoprorrogacao1 = false;
        vm.datePickerOpenStatus.dataexpedicaoprorrogacao2 = false;
        vm.datePickerOpenStatus.dataexpedicaoprorrogacao3 = false;
        vm.datePickerOpenStatus.dataoficiolocalpedido = false;
        vm.datePickerOpenStatus.dataoficiolocalrecebimento = false;
        vm.datePickerOpenStatus.dataoficioreoficialpedido = false;
        vm.datePickerOpenStatus.dataoficioreoficialrecebimento = false;
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
