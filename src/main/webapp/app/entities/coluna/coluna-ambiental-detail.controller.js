(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ColunaAmbientalDetailController', ColunaAmbientalDetailController);

    ColunaAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Coluna', 'Linha'];

    function ColunaAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Coluna, Linha) {
        var vm = this;

        vm.coluna = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:colunaUpdate', function(event, result) {
            vm.coluna = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
