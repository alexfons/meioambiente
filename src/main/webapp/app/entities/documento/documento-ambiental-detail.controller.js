(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('DocumentoAmbientalDetailController', DocumentoAmbientalDetailController);

    DocumentoAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Documento'];

    function DocumentoAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Documento) {
        var vm = this;

        vm.documento = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:documentoUpdate', function(event, result) {
            vm.documento = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
