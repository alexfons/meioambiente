(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciainformeAmbientalDetailController', OcorrenciainformeAmbientalDetailController);

    OcorrenciainformeAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Ocorrenciainforme', 'Ocorrencia'];

    function OcorrenciainformeAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Ocorrenciainforme, Ocorrencia) {
        var vm = this;

        vm.ocorrenciainforme = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:ocorrenciainformeUpdate', function(event, result) {
            vm.ocorrenciainforme = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
