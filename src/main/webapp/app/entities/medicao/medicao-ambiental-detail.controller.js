(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('MedicaoAmbientalDetailController', MedicaoAmbientalDetailController);

    MedicaoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Medicao', 'Contrato'];

    function MedicaoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Medicao, Contrato) {
        var vm = this;

        vm.medicao = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:medicaoUpdate', function(event, result) {
            vm.medicao = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
