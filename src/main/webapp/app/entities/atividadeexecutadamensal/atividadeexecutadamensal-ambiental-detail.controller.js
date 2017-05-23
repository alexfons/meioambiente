(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AtividadeexecutadamensalAmbientalDetailController', AtividadeexecutadamensalAmbientalDetailController);

    AtividadeexecutadamensalAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Atividadeexecutadamensal', 'Atividade'];

    function AtividadeexecutadamensalAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Atividadeexecutadamensal, Atividade) {
        var vm = this;

        vm.atividadeexecutadamensal = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:atividadeexecutadamensalUpdate', function(event, result) {
            vm.atividadeexecutadamensal = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
