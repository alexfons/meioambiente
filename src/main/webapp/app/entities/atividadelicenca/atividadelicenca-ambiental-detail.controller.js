(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AtividadelicencaAmbientalDetailController', AtividadelicencaAmbientalDetailController);

    AtividadelicencaAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Atividadelicenca'];

    function AtividadelicencaAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Atividadelicenca) {
        var vm = this;

        vm.atividadelicenca = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:atividadelicencaUpdate', function(event, result) {
            vm.atividadelicenca = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
