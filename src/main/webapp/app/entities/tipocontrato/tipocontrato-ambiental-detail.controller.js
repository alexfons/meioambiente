(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipocontratoAmbientalDetailController', TipocontratoAmbientalDetailController);

    TipocontratoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Tipocontrato'];

    function TipocontratoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Tipocontrato) {
        var vm = this;

        vm.tipocontrato = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:tipocontratoUpdate', function(event, result) {
            vm.tipocontrato = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
