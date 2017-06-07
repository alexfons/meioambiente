(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('QuantitativoAmbientalDetailController', QuantitativoAmbientalDetailController);

    QuantitativoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Quantitativo', 'Medicao'];

    function QuantitativoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Quantitativo, Medicao) {
        var vm = this;

        vm.quantitativo = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:quantitativoUpdate', function(event, result) {
            vm.quantitativo = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
