(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('NaturezaAmbientalDetailController', NaturezaAmbientalDetailController);

    NaturezaAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Natureza'];

    function NaturezaAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Natureza) {
        var vm = this;

        vm.natureza = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:naturezaUpdate', function(event, result) {
            vm.natureza = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
