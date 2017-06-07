(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('LogAmbientalController', LogAmbientalController);

    LogAmbientalController.$inject = ['Log'];

    function LogAmbientalController(Log) {

        var vm = this;

        vm.logs = [];

        loadAll();

        function loadAll() {
            Log.query(function(result) {
                vm.logs = result;
                vm.searchQuery = null;
            });
        }
    }
})();
