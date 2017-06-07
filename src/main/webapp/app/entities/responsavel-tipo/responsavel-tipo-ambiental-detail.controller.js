(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ResponsavelTipoAmbientalDetailController', ResponsavelTipoAmbientalDetailController);

    ResponsavelTipoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ResponsavelTipo'];

    function ResponsavelTipoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, ResponsavelTipo) {
        var vm = this;

        vm.responsavelTipo = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:responsavelTipoUpdate', function(event, result) {
            vm.responsavelTipo = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
