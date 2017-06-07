(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ReferenciaAmbientalDetailController', ReferenciaAmbientalDetailController);

    ReferenciaAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Referencia'];

    function ReferenciaAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Referencia) {
        var vm = this;

        vm.referencia = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:referenciaUpdate', function(event, result) {
            vm.referencia = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
