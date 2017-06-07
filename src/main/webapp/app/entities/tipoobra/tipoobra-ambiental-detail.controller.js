(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoobraAmbientalDetailController', TipoobraAmbientalDetailController);

    TipoobraAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Tipoobra'];

    function TipoobraAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Tipoobra) {
        var vm = this;

        vm.tipoobra = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:tipoobraUpdate', function(event, result) {
            vm.tipoobra = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
