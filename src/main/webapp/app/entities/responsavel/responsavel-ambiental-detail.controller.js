(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ResponsavelAmbientalDetailController', ResponsavelAmbientalDetailController);

    ResponsavelAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Responsavel'];

    function ResponsavelAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Responsavel) {
        var vm = this;

        vm.responsavel = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:responsavelUpdate', function(event, result) {
            vm.responsavel = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
