(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CondicionanteAmbientalDetailController', CondicionanteAmbientalDetailController);

    CondicionanteAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Condicionante'];

    function CondicionanteAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Condicionante) {
        var vm = this;

        vm.condicionante = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:condicionanteUpdate', function(event, result) {
            vm.condicionante = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
