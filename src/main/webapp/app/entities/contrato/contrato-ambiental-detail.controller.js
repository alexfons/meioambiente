(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ContratoAmbientalDetailController', ContratoAmbientalDetailController);

    ContratoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Contrato', 'Empresa', 'Planocontas', 'Natureza', 'Proposta', 'Categoriainversao', 'Aditivocontrato', 'Contratoobra', 'Empresacontrato'];

    function ContratoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Contrato, Empresa, Planocontas, Natureza, Proposta, Categoriainversao, Aditivocontrato, Contratoobra, Empresacontrato) {
        var vm = this;

        vm.contrato = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:contratoUpdate', function(event, result) {
            vm.contrato = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
