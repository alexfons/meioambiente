(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AdministrativoAmbientalDialogController', AdministrativoAmbientalDialogController);

    AdministrativoAmbientalDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Administrativo', 'Tipoadministrativo', 'Obra', 'Foto', 'Documento', 'Participante'];

    function AdministrativoAmbientalDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Administrativo, Tipoadministrativo, Obra, Foto, Documento, Participante) {
        var vm = this;

        vm.administrativo = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.tipos = Tipoadministrativo.query({filter: 'administrativo-is-null'});
        $q.all([vm.administrativo.$promise, vm.tipos.$promise]).then(function() {
            if (!vm.administrativo.tipoId) {
                return $q.reject();
            }
            return Tipoadministrativo.get({id : vm.administrativo.tipoId}).$promise;
        }).then(function(tipo) {
            vm.tipos.push(tipo);
        });
        vm.obras = Obra.query();
        vm.fotos = Foto.query();
        vm.documentos = Documento.query();
        vm.participantes = Participante.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.administrativo.id !== null) {
                Administrativo.update(vm.administrativo, onSaveSuccess, onSaveError);
            } else {
                Administrativo.save(vm.administrativo, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('meioambienteApp:administrativoUpdate', result);
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
