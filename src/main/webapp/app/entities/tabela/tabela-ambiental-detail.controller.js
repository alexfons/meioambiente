(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TabelaAmbientalDetailController', TabelaAmbientalDetailController);

    TabelaAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Tabela'];

    function TabelaAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Tabela) {
        var vm = this;

        vm.tabela = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:tabelaUpdate', function(event, result) {
            vm.tabela = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
