(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciaAmbientalDetailController', OcorrenciaAmbientalDetailController);

    OcorrenciaAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Ocorrencia', 'Atividade', 'Obra', 'Tabela', 'Tipoocorrencia', 'Foto', 'Historico', 'Registro'];

    function OcorrenciaAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Ocorrencia, Atividade, Obra, Tabela, Tipoocorrencia, Foto, Historico, Registro) {
        var vm = this;

        vm.ocorrencia = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:ocorrenciaUpdate', function(event, result) {
            vm.ocorrencia = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
