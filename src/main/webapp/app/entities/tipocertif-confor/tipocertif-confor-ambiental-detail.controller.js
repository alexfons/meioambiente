(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipocertifConforAmbientalDetailController', TipocertifConforAmbientalDetailController);

    TipocertifConforAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'TipocertifConfor'];

    function TipocertifConforAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, TipocertifConfor) {
        var vm = this;

        vm.tipocertifConfor = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:tipocertifConforUpdate', function(event, result) {
            vm.tipocertifConfor = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
