(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoAmbientalDetailController', TipoAmbientalDetailController);

    TipoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Tipo'];

    function TipoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Tipo) {
        var vm = this;

        vm.tipo = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:tipoUpdate', function(event, result) {
            vm.tipo = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
