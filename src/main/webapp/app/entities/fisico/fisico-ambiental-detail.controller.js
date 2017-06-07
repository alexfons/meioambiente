(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FisicoAmbientalDetailController', FisicoAmbientalDetailController);

    FisicoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Fisico', 'Obra', 'Obraatividade'];

    function FisicoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Fisico, Obra, Obraatividade) {
        var vm = this;

        vm.fisico = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:fisicoUpdate', function(event, result) {
            vm.fisico = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
