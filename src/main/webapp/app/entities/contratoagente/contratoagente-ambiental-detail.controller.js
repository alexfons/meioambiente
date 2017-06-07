(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ContratoagenteAmbientalDetailController', ContratoagenteAmbientalDetailController);

    ContratoagenteAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Contratoagente'];

    function ContratoagenteAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Contratoagente) {
        var vm = this;

        vm.contratoagente = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:contratoagenteUpdate', function(event, result) {
            vm.contratoagente = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
