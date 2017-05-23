(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OrgaoemissorAmbientalDetailController', OrgaoemissorAmbientalDetailController);

    OrgaoemissorAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Orgaoemissor'];

    function OrgaoemissorAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Orgaoemissor) {
        var vm = this;

        vm.orgaoemissor = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:orgaoemissorUpdate', function(event, result) {
            vm.orgaoemissor = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
