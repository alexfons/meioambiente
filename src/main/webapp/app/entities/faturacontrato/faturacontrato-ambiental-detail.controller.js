(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FaturacontratoAmbientalDetailController', FaturacontratoAmbientalDetailController);

    FaturacontratoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Faturacontrato', 'Fonte', 'Contrato', 'Referenciacontrato'];

    function FaturacontratoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Faturacontrato, Fonte, Contrato, Referenciacontrato) {
        var vm = this;

        vm.faturacontrato = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:faturacontratoUpdate', function(event, result) {
            vm.faturacontrato = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
