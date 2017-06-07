(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoocorrenciaAmbientalDetailController', TipoocorrenciaAmbientalDetailController);

    TipoocorrenciaAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Tipoocorrencia'];

    function TipoocorrenciaAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Tipoocorrencia) {
        var vm = this;

        vm.tipoocorrencia = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:tipoocorrenciaUpdate', function(event, result) {
            vm.tipoocorrencia = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
