(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('PagfuncionarioAmbientalDetailController', PagfuncionarioAmbientalDetailController);

    PagfuncionarioAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Pagfuncionario', 'Planocontas', 'Fonte', 'Categoriainversao', 'Funcionario', 'Referencia'];

    function PagfuncionarioAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Pagfuncionario, Planocontas, Fonte, Categoriainversao, Funcionario, Referencia) {
        var vm = this;

        vm.pagfuncionario = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:pagfuncionarioUpdate', function(event, result) {
            vm.pagfuncionario = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
