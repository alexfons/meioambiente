(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ClausulascontratuaisAmbientalDetailController', ClausulascontratuaisAmbientalDetailController);

    ClausulascontratuaisAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Clausulascontratuais', 'Contratoagente'];

    function ClausulascontratuaisAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Clausulascontratuais, Contratoagente) {
        var vm = this;

        vm.clausulascontratuais = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:clausulascontratuaisUpdate', function(event, result) {
            vm.clausulascontratuais = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
