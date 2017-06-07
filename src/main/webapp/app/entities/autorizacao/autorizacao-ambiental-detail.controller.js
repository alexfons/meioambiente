(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AutorizacaoAmbientalDetailController', AutorizacaoAmbientalDetailController);

    AutorizacaoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Autorizacao', 'Atividadelicenca', 'Empresa', 'Obra', 'Orgaoemissor', 'Projeto', 'Tipoautorizacao', 'Foto', 'Documento'];

    function AutorizacaoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Autorizacao, Atividadelicenca, Empresa, Obra, Orgaoemissor, Projeto, Tipoautorizacao, Foto, Documento) {
        var vm = this;

        vm.autorizacao = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:autorizacaoUpdate', function(event, result) {
            vm.autorizacao = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
