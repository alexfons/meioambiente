(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('LinhaAmbientalDetailController', LinhaAmbientalDetailController);

    LinhaAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Linha', 'Coluna'];

    function LinhaAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Linha, Coluna) {
        var vm = this;

        vm.linha = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:linhaUpdate', function(event, result) {
            vm.linha = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
