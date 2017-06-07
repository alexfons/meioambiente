(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ReferenciacontratoAmbientalDetailController', ReferenciacontratoAmbientalDetailController);

    ReferenciacontratoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Referenciacontrato'];

    function ReferenciacontratoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Referenciacontrato) {
        var vm = this;

        vm.referenciacontrato = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:referenciacontratoUpdate', function(event, result) {
            vm.referenciacontrato = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
