(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FaturaAmbientalDetailController', FaturaAmbientalDetailController);

    FaturaAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Fatura', 'Fonte', 'Contabancaria', 'Contrato', 'Referencia', 'Medicao'];

    function FaturaAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Fatura, Fonte, Contabancaria, Contrato, Referencia, Medicao) {
        var vm = this;

        vm.fatura = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:faturaUpdate', function(event, result) {
            vm.fatura = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
