(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('InspetorAmbientalDetailController', InspetorAmbientalDetailController);

    InspetorAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Inspetor'];

    function InspetorAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Inspetor) {
        var vm = this;

        vm.inspetor = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:inspetorUpdate', function(event, result) {
            vm.inspetor = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
