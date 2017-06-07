(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('PendenciasAmbientalDetailController', PendenciasAmbientalDetailController);

    PendenciasAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Pendencias', 'Obra', 'Foto', 'Ocorrenciapendencias'];

    function PendenciasAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Pendencias, Obra, Foto, Ocorrenciapendencias) {
        var vm = this;

        vm.pendencias = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:pendenciasUpdate', function(event, result) {
            vm.pendencias = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
