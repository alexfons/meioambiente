(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AditivocontratoAmbientalDetailController', AditivocontratoAmbientalDetailController);

    AditivocontratoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Aditivocontrato'];

    function AditivocontratoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Aditivocontrato) {
        var vm = this;

        vm.aditivocontrato = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:aditivocontratoUpdate', function(event, result) {
            vm.aditivocontrato = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
