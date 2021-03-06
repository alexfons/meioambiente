(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('EmpresacontratoAmbientalDetailController', EmpresacontratoAmbientalDetailController);

    EmpresacontratoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Empresacontrato', 'Empresa'];

    function EmpresacontratoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Empresacontrato, Empresa) {
        var vm = this;

        vm.empresacontrato = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:empresacontratoUpdate', function(event, result) {
            vm.empresacontrato = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
