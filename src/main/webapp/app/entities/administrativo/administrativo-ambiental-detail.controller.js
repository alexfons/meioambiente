(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AdministrativoAmbientalDetailController', AdministrativoAmbientalDetailController);

    AdministrativoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Administrativo', 'Tipoadministrativo', 'Obra', 'Foto', 'Documento', 'Participante'];

    function AdministrativoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Administrativo, Tipoadministrativo, Obra, Foto, Documento, Participante) {
        var vm = this;

        vm.administrativo = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:administrativoUpdate', function(event, result) {
            vm.administrativo = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
