(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('InspetorAmbientalController', InspetorAmbientalController);

    InspetorAmbientalController.$inject = ['Inspetor'];

    function InspetorAmbientalController(Inspetor) {

        var vm = this;

        vm.inspetors = [];

        loadAll();

        function loadAll() {
            Inspetor.query(function(result) {
                vm.inspetors = result;
                vm.searchQuery = null;
            });
        }
    }
})();
