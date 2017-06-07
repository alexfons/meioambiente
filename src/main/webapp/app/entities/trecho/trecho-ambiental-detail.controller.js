(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TrechoAmbientalDetailController', TrechoAmbientalDetailController);

    TrechoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Trecho', 'Rodovia', 'Supre'];

    function TrechoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Trecho, Rodovia, Supre) {
        var vm = this;

        vm.trecho = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:trechoUpdate', function(event, result) {
            vm.trecho = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
