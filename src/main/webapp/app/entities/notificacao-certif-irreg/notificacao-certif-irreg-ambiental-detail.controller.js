(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('NotificacaoCertifIrregAmbientalDetailController', NotificacaoCertifIrregAmbientalDetailController);

    NotificacaoCertifIrregAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'NotificacaoCertifIrreg'];

    function NotificacaoCertifIrregAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, NotificacaoCertifIrreg) {
        var vm = this;

        vm.notificacaoCertifIrreg = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:notificacaoCertifIrregUpdate', function(event, result) {
            vm.notificacaoCertifIrreg = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
