(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CustosconcorrentesAmbientalDetailController', CustosconcorrentesAmbientalDetailController);

    CustosconcorrentesAmbientalDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Custosconcorrentes', 'Planocontas', 'Fonte', 'Categoriainversao'];

    function CustosconcorrentesAmbientalDetailController($scope, $rootScope, $stateParams, previousState, entity, Custosconcorrentes, Planocontas, Fonte, Categoriainversao) {
        var vm = this;

        vm.custosconcorrentes = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('meioambienteApp:custosconcorrentesUpdate', function(event, result) {
            vm.custosconcorrentes = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
