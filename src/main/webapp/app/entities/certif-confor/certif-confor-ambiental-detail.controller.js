(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CertifConforAmbientalDetailController', CertifConforAmbientalDetailController);

    CertifConforAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'CertifConfor', 'Obra', 'TipocertifConfor', 'InformeCertifIrreg', 'NotificacaoCertifIrreg', 'OcorrenciaCertifIrreg'];

    function CertifConforAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, CertifConfor, Obra, TipocertifConfor, InformeCertifIrreg, NotificacaoCertifIrreg, OcorrenciaCertifIrreg) {
        var vm = this;

        vm.certifConfor = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:certifConforUpdate', function(event, result) {
            vm.certifConfor = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
