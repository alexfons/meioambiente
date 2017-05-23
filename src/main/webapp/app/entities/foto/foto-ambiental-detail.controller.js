(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FotoAmbientalDetailController', FotoAmbientalDetailController);

    FotoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Foto'];

    function FotoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Foto) {
        var vm = this;

        vm.foto = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:fotoUpdate', function(event, result) {
            vm.foto = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
