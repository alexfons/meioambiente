(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ManifestacaoAmbientalDetailController', ManifestacaoAmbientalDetailController);

    ManifestacaoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Manifestacao'];

    function ManifestacaoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Manifestacao) {
        var vm = this;

        vm.manifestacao = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:manifestacaoUpdate', function(event, result) {
            vm.manifestacao = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
