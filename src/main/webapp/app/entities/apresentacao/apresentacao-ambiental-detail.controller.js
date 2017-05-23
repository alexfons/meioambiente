(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ApresentacaoAmbientalDetailController', ApresentacaoAmbientalDetailController);

    ApresentacaoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Apresentacao', 'Obra', 'Ocorrenciaapresentacao', 'Foto'];

    function ApresentacaoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Apresentacao, Obra, Ocorrenciaapresentacao, Foto) {
        var vm = this;

        vm.apresentacao = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:apresentacaoUpdate', function(event, result) {
            vm.apresentacao = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
