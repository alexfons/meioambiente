(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CertificadoconformidadeAmbientalDetailController', CertificadoconformidadeAmbientalDetailController);

    CertificadoconformidadeAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Certificadoconformidade', 'Obra', 'Tipocertificadoconformidade', 'Informecertificadoirregularidade', 'Notificacaocertificadoirregularidade', 'Ocorrenciacertificadoirregularidade'];

    function CertificadoconformidadeAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Certificadoconformidade, Obra, Tipocertificadoconformidade, Informecertificadoirregularidade, Notificacaocertificadoirregularidade, Ocorrenciacertificadoirregularidade) {
        var vm = this;

        vm.certificadoconformidade = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:certificadoconformidadeUpdate', function(event, result) {
            vm.certificadoconformidade = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
