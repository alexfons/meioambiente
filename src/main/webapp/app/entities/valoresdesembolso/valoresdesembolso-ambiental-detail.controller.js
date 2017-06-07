(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ValoresdesembolsoAmbientalDetailController', ValoresdesembolsoAmbientalDetailController);

    ValoresdesembolsoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Valoresdesembolso', 'Contabancaria', 'Referencia'];

    function ValoresdesembolsoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Valoresdesembolso, Contabancaria, Referencia) {
        var vm = this;

        vm.valoresdesembolso = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:valoresdesembolsoUpdate', function(event, result) {
            vm.valoresdesembolso = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
