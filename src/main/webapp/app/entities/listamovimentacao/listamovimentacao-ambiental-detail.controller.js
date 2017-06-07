(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ListamovimentacaoAmbientalDetailController', ListamovimentacaoAmbientalDetailController);

    ListamovimentacaoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Listamovimentacao', 'Planocontas', 'Movimentacao'];

    function ListamovimentacaoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Listamovimentacao, Planocontas, Movimentacao) {
        var vm = this;

        vm.listamovimentacao = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:listamovimentacaoUpdate', function(event, result) {
            vm.listamovimentacao = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
