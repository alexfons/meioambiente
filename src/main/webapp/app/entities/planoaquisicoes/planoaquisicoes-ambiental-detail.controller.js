(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('PlanoaquisicoesAmbientalDetailController', PlanoaquisicoesAmbientalDetailController);

    PlanoaquisicoesAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Planoaquisicoes', 'Fonte', 'Contratoagente', 'Edital'];

    function PlanoaquisicoesAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Planoaquisicoes, Fonte, Contratoagente, Edital) {
        var vm = this;

        vm.planoaquisicoes = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:planoaquisicoesUpdate', function(event, result) {
            vm.planoaquisicoes = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
