(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('NotificacaoAmbientalDetailController', NotificacaoAmbientalDetailController);

    NotificacaoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Notificacao', 'Obra', 'Foto', 'Ocorrencianotificacao'];

    function NotificacaoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Notificacao, Obra, Foto, Ocorrencianotificacao) {
        var vm = this;

        vm.notificacao = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:notificacaoUpdate', function(event, result) {
            vm.notificacao = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
