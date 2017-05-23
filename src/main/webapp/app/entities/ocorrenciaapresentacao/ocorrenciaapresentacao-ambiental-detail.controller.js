(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciaapresentacaoAmbientalDetailController', OcorrenciaapresentacaoAmbientalDetailController);

    OcorrenciaapresentacaoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Ocorrenciaapresentacao'];

    function OcorrenciaapresentacaoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Ocorrenciaapresentacao) {
        var vm = this;

        vm.ocorrenciaapresentacao = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:ocorrenciaapresentacaoUpdate', function(event, result) {
            vm.ocorrenciaapresentacao = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
