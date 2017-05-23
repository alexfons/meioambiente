(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AucAmbientalDetailController', AucAmbientalDetailController);

    AucAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Auc', 'Atividadelicenca', 'Orgaoemissor', 'Obra', 'Projeto', 'Empresa', 'Condicionante', 'Foto', 'Documento'];

    function AucAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Auc, Atividadelicenca, Orgaoemissor, Obra, Projeto, Empresa, Condicionante, Foto, Documento) {
        var vm = this;

        vm.auc = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:aucUpdate', function(event, result) {
            vm.auc = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
