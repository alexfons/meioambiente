(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FiscalAmbientalDetailController', FiscalAmbientalDetailController);

    FiscalAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Fiscal'];

    function FiscalAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Fiscal) {
        var vm = this;

        vm.fiscal = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:fiscalUpdate', function(event, result) {
            vm.fiscal = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
