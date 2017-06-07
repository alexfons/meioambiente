(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('NotificacaocertificadoirregularidadeAmbientalDetailController', NotificacaocertificadoirregularidadeAmbientalDetailController);

    NotificacaocertificadoirregularidadeAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Notificacaocertificadoirregularidade'];

    function NotificacaocertificadoirregularidadeAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Notificacaocertificadoirregularidade) {
        var vm = this;

        vm.notificacaocertificadoirregularidade = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:notificacaocertificadoirregularidadeUpdate', function(event, result) {
            vm.notificacaocertificadoirregularidade = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
