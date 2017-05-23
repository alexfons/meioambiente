(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ContratoobraAmbientalDetailController', ContratoobraAmbientalDetailController);

    ContratoobraAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Contratoobra'];

    function ContratoobraAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Contratoobra) {
        var vm = this;

        vm.contratoobra = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:contratoobraUpdate', function(event, result) {
            vm.contratoobra = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
