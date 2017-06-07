(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CoordenadaAmbientalDetailController', CoordenadaAmbientalDetailController);

    CoordenadaAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Coordenada'];

    function CoordenadaAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Coordenada) {
        var vm = this;

        vm.coordenada = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:coordenadaUpdate', function(event, result) {
            vm.coordenada = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
