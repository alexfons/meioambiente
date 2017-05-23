(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ProjetoAmbientalDetailController', ProjetoAmbientalDetailController);

    ProjetoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Projeto'];

    function ProjetoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Projeto) {
        var vm = this;

        vm.projeto = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:projetoUpdate', function(event, result) {
            vm.projeto = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
