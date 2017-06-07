(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CategoriainversaoAmbientalDetailController', CategoriainversaoAmbientalDetailController);

    CategoriainversaoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Categoriainversao', 'Contratoagente'];

    function CategoriainversaoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Categoriainversao, Contratoagente) {
        var vm = this;

        vm.categoriainversao = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:categoriainversaoUpdate', function(event, result) {
            vm.categoriainversao = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
