(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciapendenciasAmbientalDetailController', OcorrenciapendenciasAmbientalDetailController);

    OcorrenciapendenciasAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Ocorrenciapendencias', 'Ocorrencia'];

    function OcorrenciapendenciasAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Ocorrenciapendencias, Ocorrencia) {
        var vm = this;

        vm.ocorrenciapendencias = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:ocorrenciapendenciasUpdate', function(event, result) {
            vm.ocorrenciapendencias = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
