(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('LogAmbientalDetailController', LogAmbientalDetailController);

    LogAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Log', 'Usuario'];

    function LogAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Log, Usuario) {
        var vm = this;

        vm.log = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:logUpdate', function(event, result) {
            vm.log = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
