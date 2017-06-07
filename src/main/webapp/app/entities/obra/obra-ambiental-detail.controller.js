(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ObraAmbientalDetailController', ObraAmbientalDetailController);

    ObraAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Obra', 'Tipoobra', 'Inspetor', 'Fiscal', 'Trecho', 'Contratoobra', 'Historico'];

    function ObraAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Obra, Tipoobra, Inspetor, Fiscal, Trecho, Contratoobra, Historico) {
        var vm = this;

        vm.obra = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:obraUpdate', function(event, result) {
            vm.obra = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
