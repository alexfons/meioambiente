(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoObraAmbientalDetailController', TipoObraAmbientalDetailController);

    TipoObraAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'TipoObra'];

    function TipoObraAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, TipoObra) {
        var vm = this;

        vm.tipoObra = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:tipoObraUpdate', function(event, result) {
            vm.tipoObra = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
