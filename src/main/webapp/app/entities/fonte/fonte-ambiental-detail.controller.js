(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FonteAmbientalDetailController', FonteAmbientalDetailController);

    FonteAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Fonte', 'Contabancaria'];

    function FonteAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Fonte, Contabancaria) {
        var vm = this;

        vm.fonte = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:fonteUpdate', function(event, result) {
            vm.fonte = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
