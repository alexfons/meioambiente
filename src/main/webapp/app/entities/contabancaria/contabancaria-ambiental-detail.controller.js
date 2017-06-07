(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ContabancariaAmbientalDetailController', ContabancariaAmbientalDetailController);

    ContabancariaAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Contabancaria', 'Planocontas'];

    function ContabancariaAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Contabancaria, Planocontas) {
        var vm = this;

        vm.contabancaria = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:contabancariaUpdate', function(event, result) {
            vm.contabancaria = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
