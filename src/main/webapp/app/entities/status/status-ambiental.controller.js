(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('StatusAmbientalController', StatusAmbientalController);

    StatusAmbientalController.$inject = ['Status'];

    function StatusAmbientalController(Status) {

        var vm = this;

        vm.statuses = [];

        loadAll();

        function loadAll() {
            Status.query(function(result) {
                vm.statuses = result;
                vm.searchQuery = null;
            });
        }
    }
})();
