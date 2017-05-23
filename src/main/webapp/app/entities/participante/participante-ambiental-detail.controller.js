(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ParticipanteAmbientalDetailController', ParticipanteAmbientalDetailController);

    ParticipanteAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Participante'];

    function ParticipanteAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Participante) {
        var vm = this;

        vm.participante = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:participanteUpdate', function(event, result) {
            vm.participante = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
