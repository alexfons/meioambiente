(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ResidenteAmbientalDetailController', ResidenteAmbientalDetailController);

    ResidenteAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Residente'];

    function ResidenteAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Residente) {
        var vm = this;

        vm.residente = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:residenteUpdate', function(event, result) {
            vm.residente = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
