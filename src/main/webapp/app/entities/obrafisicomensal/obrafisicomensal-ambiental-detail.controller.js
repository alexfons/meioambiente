(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ObrafisicomensalAmbientalDetailController', ObrafisicomensalAmbientalDetailController);

    ObrafisicomensalAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Obrafisicomensal', 'Fisico', 'Atividadeexecutadamensal'];

    function ObrafisicomensalAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Obrafisicomensal, Fisico, Atividadeexecutadamensal) {
        var vm = this;

        vm.obrafisicomensal = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:obrafisicomensalUpdate', function(event, result) {
            vm.obrafisicomensal = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
