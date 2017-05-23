(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('EmpresaAmbientalDetailController', EmpresaAmbientalDetailController);

    EmpresaAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Empresa'];

    function EmpresaAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Empresa) {
        var vm = this;

        vm.empresa = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:empresaUpdate', function(event, result) {
            vm.empresa = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
