(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ScriptAmbientalDetailController', ScriptAmbientalDetailController);

    ScriptAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Script'];

    function ScriptAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Script) {
        var vm = this;

        vm.script = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:scriptUpdate', function(event, result) {
            vm.script = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
