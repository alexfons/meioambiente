(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('UsuarioAmbientalDetailController', UsuarioAmbientalDetailController);

    UsuarioAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Usuario'];

    function UsuarioAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Usuario) {
        var vm = this;

        vm.usuario = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:usuarioUpdate', function(event, result) {
            vm.usuario = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
