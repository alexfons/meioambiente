(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('PropostaAmbientalDetailController', PropostaAmbientalDetailController);

    PropostaAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Proposta', 'Empresa', 'Editallote'];

    function PropostaAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Proposta, Empresa, Editallote) {
        var vm = this;

        vm.proposta = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:propostaUpdate', function(event, result) {
            vm.proposta = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
