(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('InformecertificadoirregularidadeAmbientalDetailController', InformecertificadoirregularidadeAmbientalDetailController);

    InformecertificadoirregularidadeAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Informecertificadoirregularidade', 'Informe'];

    function InformecertificadoirregularidadeAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Informecertificadoirregularidade, Informe) {
        var vm = this;

        vm.informecertificadoirregularidade = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:informecertificadoirregularidadeUpdate', function(event, result) {
            vm.informecertificadoirregularidade = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
