(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('InformeAmbientalDetailController', InformeAmbientalDetailController);

    InformeAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Informe', 'Obra', 'Foto', 'Ocorrenciainforme'];

    function InformeAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Informe, Obra, Foto, Ocorrenciainforme) {
        var vm = this;

        vm.informe = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:informeUpdate', function(event, result) {
            vm.informe = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
