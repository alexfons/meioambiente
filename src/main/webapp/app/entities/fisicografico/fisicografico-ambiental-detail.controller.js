(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FisicograficoAmbientalDetailController', FisicograficoAmbientalDetailController);

    FisicograficoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Fisicografico', 'Atividade'];

    function FisicograficoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Fisicografico, Atividade) {
        var vm = this;

        vm.fisicografico = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:fisicograficoUpdate', function(event, result) {
            vm.fisicografico = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
