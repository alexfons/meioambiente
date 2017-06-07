(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('PlanocontasAmbientalDetailController', PlanocontasAmbientalDetailController);

    PlanocontasAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Planocontas'];

    function PlanocontasAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Planocontas) {
        var vm = this;

        vm.planocontas = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:planocontasUpdate', function(event, result) {
            vm.planocontas = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
