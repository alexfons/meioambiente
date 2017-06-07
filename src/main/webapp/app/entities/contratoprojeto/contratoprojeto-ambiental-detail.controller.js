(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ContratoprojetoAmbientalDetailController', ContratoprojetoAmbientalDetailController);

    ContratoprojetoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Contratoprojeto', 'Contrato', 'Responsavel'];

    function ContratoprojetoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Contratoprojeto, Contrato, Responsavel) {
        var vm = this;

        vm.contratoprojeto = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:contratoprojetoUpdate', function(event, result) {
            vm.contratoprojeto = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
