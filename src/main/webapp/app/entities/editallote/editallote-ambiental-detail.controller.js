(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('EditalloteAmbientalDetailController', EditalloteAmbientalDetailController);

    EditalloteAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Editallote', 'Edital'];

    function EditalloteAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Editallote, Edital) {
        var vm = this;

        vm.editallote = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:editalloteUpdate', function(event, result) {
            vm.editallote = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
