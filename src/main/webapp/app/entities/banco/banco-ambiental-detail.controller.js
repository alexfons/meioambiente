(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('BancoAmbientalDetailController', BancoAmbientalDetailController);

    BancoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Banco'];

    function BancoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Banco) {
        var vm = this;

        vm.banco = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:bancoUpdate', function(event, result) {
            vm.banco = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
