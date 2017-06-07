(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ProjetoAmbientalDetailController', ProjetoAmbientalDetailController);

    ProjetoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Projeto', 'Inspetor', 'Municipio', 'Tipoobra', 'Trecho', 'Fiscal', 'Contratoprojeto', 'Historico'];

    function ProjetoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Projeto, Inspetor, Municipio, Tipoobra, Trecho, Fiscal, Contratoprojeto, Historico) {
        var vm = this;

        vm.projeto = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:projetoUpdate', function(event, result) {
            vm.projeto = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
