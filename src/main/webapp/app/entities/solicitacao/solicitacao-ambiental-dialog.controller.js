(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('SolicitacaoAmbientalDialogController', SolicitacaoAmbientalDialogController);

    SolicitacaoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Solicitacao', 'Banco', 'Contratoagente'];

    function SolicitacaoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Solicitacao, Banco, Contratoagente) {
        var vm = this;

        vm.solicitacao = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.idbancos = Banco.query({filter: 'solicitacao-is-null'});
        $q.all([vm.solicitacao.$promise, vm.idbancos.$promise]).then(function() {
            if (!vm.solicitacao.idbancoId) {
                return $q.reject();
            }
            return Banco.get({id : vm.solicitacao.idbancoId}).$promise;
        }).then(function(idbanco) {
            vm.idbancos.push(idbanco);
        });
        vm.contratoagentes = Contratoagente.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.solicitacao.id !== null) {
                Solicitacao.update(vm.solicitacao, onSaveSuccess, onSaveError);
            } else {
                Solicitacao.save(vm.solicitacao, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:solicitacaoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.data = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
