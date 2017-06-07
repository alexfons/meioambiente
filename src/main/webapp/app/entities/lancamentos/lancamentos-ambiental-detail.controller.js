(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('LancamentosAmbientalDetailController', LancamentosAmbientalDetailController);

    LancamentosAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Lancamentos', 'Fonte', 'Planocontas'];

    function LancamentosAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Lancamentos, Fonte, Planocontas) {
        var vm = this;

        vm.lancamentos = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:lancamentosUpdate', function(event, result) {
            vm.lancamentos = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
