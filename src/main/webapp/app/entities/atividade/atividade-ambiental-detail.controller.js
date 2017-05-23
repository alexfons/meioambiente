(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AtividadeAmbientalDetailController', AtividadeAmbientalDetailController);

    AtividadeAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Atividade'];

    function AtividadeAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Atividade) {
        var vm = this;

        vm.atividade = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:atividadeUpdate', function(event, result) {
            vm.atividade = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
