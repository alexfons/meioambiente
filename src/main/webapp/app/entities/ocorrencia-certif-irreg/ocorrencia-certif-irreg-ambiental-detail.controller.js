(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciaCertifIrregAmbientalDetailController', OcorrenciaCertifIrregAmbientalDetailController);

    OcorrenciaCertifIrregAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'OcorrenciaCertifIrreg', 'Ocorrencia'];

    function OcorrenciaCertifIrregAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, OcorrenciaCertifIrreg, Ocorrencia) {
        var vm = this;

        vm.ocorrenciaCertifIrreg = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:ocorrenciaCertifIrregUpdate', function(event, result) {
            vm.ocorrenciaCertifIrreg = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
