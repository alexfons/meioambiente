(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrencianotificacaoAmbientalDetailController', OcorrencianotificacaoAmbientalDetailController);

    OcorrencianotificacaoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Ocorrencianotificacao', 'Ocorrencia'];

    function OcorrencianotificacaoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Ocorrencianotificacao, Ocorrencia) {
        var vm = this;

        vm.ocorrencianotificacao = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:ocorrencianotificacaoUpdate', function(event, result) {
            vm.ocorrencianotificacao = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
