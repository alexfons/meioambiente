(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('MunicipioAmbientalDetailController', MunicipioAmbientalDetailController);

    MunicipioAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Municipio'];

    function MunicipioAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Municipio) {
        var vm = this;

        vm.municipio = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:municipioUpdate', function(event, result) {
            vm.municipio = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
