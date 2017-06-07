(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipocertificadoconformidadeAmbientalDetailController', TipocertificadoconformidadeAmbientalDetailController);

    TipocertificadoconformidadeAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Tipocertificadoconformidade'];

    function TipocertificadoconformidadeAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Tipocertificadoconformidade) {
        var vm = this;

        vm.tipocertificadoconformidade = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:tipocertificadoconformidadeUpdate', function(event, result) {
            vm.tipocertificadoconformidade = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
