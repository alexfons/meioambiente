(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('RodoviaAmbientalDetailController', RodoviaAmbientalDetailController);

    RodoviaAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Rodovia'];

    function RodoviaAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Rodovia) {
        var vm = this;

        vm.rodovia = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:rodoviaUpdate', function(event, result) {
            vm.rodovia = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
