(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('RegistroAmbientalDetailController', RegistroAmbientalDetailController);

    RegistroAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Registro'];

    function RegistroAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Registro) {
        var vm = this;

        vm.registro = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:registroUpdate', function(event, result) {
            vm.registro = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
