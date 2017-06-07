(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('LicencaAmbientalDetailController', LicencaAmbientalDetailController);

    LicencaAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Licenca', 'Atividadelicenca', 'Empresa', 'Projeto', 'Tipolicenca', 'Obra', 'Orgaoemissor', 'Condicionante', 'Documento', 'Foto'];

    function LicencaAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Licenca, Atividadelicenca, Empresa, Projeto, Tipolicenca, Obra, Orgaoemissor, Condicionante, Documento, Foto) {
        var vm = this;

        vm.licenca = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:licencaUpdate', function(event, result) {
            vm.licenca = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
