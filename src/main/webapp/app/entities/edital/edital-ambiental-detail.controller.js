(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('EditalAmbientalDetailController', EditalAmbientalDetailController);

    EditalAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Edital'];

    function EditalAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Edital) {
        var vm = this;

        vm.edital = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:editalUpdate', function(event, result) {
            vm.edital = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
