(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoadministrativoAmbientalDetailController', TipoadministrativoAmbientalDetailController);

    TipoadministrativoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Tipoadministrativo'];

    function TipoadministrativoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Tipoadministrativo) {
        var vm = this;

        vm.tipoadministrativo = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:tipoadministrativoUpdate', function(event, result) {
            vm.tipoadministrativo = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
