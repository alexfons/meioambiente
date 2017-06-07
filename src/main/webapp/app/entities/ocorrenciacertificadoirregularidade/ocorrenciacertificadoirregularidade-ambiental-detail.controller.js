(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciacertificadoirregularidadeAmbientalDetailController', OcorrenciacertificadoirregularidadeAmbientalDetailController);

    OcorrenciacertificadoirregularidadeAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Ocorrenciacertificadoirregularidade'];

    function OcorrenciacertificadoirregularidadeAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Ocorrenciacertificadoirregularidade) {
        var vm = this;

        vm.ocorrenciacertificadoirregularidade = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:ocorrenciacertificadoirregularidadeUpdate', function(event, result) {
            vm.ocorrenciacertificadoirregularidade = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
