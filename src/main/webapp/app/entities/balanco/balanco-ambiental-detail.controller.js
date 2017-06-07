(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('BalancoAmbientalDetailController', BalancoAmbientalDetailController);

    BalancoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Balanco'];

    function BalancoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Balanco) {
        var vm = this;

        vm.balanco = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:balancoUpdate', function(event, result) {
            vm.balanco = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
