(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('SupreAmbientalDetailController', SupreAmbientalDetailController);

    SupreAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Supre'];

    function SupreAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Supre) {
        var vm = this;

        vm.supre = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:supreUpdate', function(event, result) {
            vm.supre = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
