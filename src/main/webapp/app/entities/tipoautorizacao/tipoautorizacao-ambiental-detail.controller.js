(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoautorizacaoAmbientalDetailController', TipoautorizacaoAmbientalDetailController);

    TipoautorizacaoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Tipoautorizacao'];

    function TipoautorizacaoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Tipoautorizacao) {
        var vm = this;

        vm.tipoautorizacao = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:tipoautorizacaoUpdate', function(event, result) {
            vm.tipoautorizacao = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
