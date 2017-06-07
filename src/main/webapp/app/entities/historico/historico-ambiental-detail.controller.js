(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('HistoricoAmbientalDetailController', HistoricoAmbientalDetailController);

    HistoricoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Historico', 'Status'];

    function HistoricoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Historico, Status) {
        var vm = this;

        vm.historico = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:historicoUpdate', function(event, result) {
            vm.historico = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
