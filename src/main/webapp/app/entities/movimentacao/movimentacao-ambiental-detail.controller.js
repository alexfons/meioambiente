(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('MovimentacaoAmbientalDetailController', MovimentacaoAmbientalDetailController);

    MovimentacaoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Movimentacao', 'Listamovimentacao', 'Contabancaria', 'Fonte'];

    function MovimentacaoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Movimentacao, Listamovimentacao, Contabancaria, Fonte) {
        var vm = this;

        vm.movimentacao = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:movimentacaoUpdate', function(event, result) {
            vm.movimentacao = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
