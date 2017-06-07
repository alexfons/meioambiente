(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipolicencaAmbientalDetailController', TipolicencaAmbientalDetailController);

    TipolicencaAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Tipolicenca'];

    function TipolicencaAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Tipolicenca) {
        var vm = this;

        vm.tipolicenca = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:tipolicencaUpdate', function(event, result) {
            vm.tipolicenca = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
