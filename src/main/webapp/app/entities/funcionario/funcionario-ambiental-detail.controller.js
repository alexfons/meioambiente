(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FuncionarioAmbientalDetailController', FuncionarioAmbientalDetailController);

    FuncionarioAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Funcionario'];

    function FuncionarioAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Funcionario) {
        var vm = this;

        vm.funcionario = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:funcionarioUpdate', function(event, result) {
            vm.funcionario = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
