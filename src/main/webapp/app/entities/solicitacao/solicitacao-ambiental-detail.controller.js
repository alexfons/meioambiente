(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('SolicitacaoAmbientalDetailController', SolicitacaoAmbientalDetailController);

    SolicitacaoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Solicitacao', 'Banco', 'Contratoagente'];

    function SolicitacaoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Solicitacao, Banco, Contratoagente) {
        var vm = this;

        vm.solicitacao = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:solicitacaoUpdate', function(event, result) {
            vm.solicitacao = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
