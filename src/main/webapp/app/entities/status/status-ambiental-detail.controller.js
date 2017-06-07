(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('StatusAmbientalDetailController', StatusAmbientalDetailController);

    StatusAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Status'];

    function StatusAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Status) {
        var vm = this;

        vm.status = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:statusUpdate', function(event, result) {
            vm.status = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
