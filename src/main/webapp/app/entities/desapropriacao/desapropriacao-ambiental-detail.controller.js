(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('DesapropriacaoAmbientalDetailController', DesapropriacaoAmbientalDetailController);

    DesapropriacaoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Desapropriacao', 'Planocontas', 'Fonte', 'Categoriainversao', 'Contabancaria', 'Referencia', 'Rodovia'];

    function DesapropriacaoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Desapropriacao, Planocontas, Fonte, Categoriainversao, Contabancaria, Referencia, Rodovia) {
        var vm = this;

        vm.desapropriacao = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:desapropriacaoUpdate', function(event, result) {
            vm.desapropriacao = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
