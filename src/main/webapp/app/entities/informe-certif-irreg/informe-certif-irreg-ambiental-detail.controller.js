(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('InformeCertifIrregAmbientalDetailController', InformeCertifIrregAmbientalDetailController);

    InformeCertifIrregAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'InformeCertifIrreg', 'Informe'];

    function InformeCertifIrregAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, InformeCertifIrreg, Informe) {
        var vm = this;

        vm.informeCertifIrreg = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:informeCertifIrregUpdate', function(event, result) {
            vm.informeCertifIrreg = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
