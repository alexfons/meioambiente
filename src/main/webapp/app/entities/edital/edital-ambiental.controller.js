(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('EditalAmbientalController', EditalAmbientalController);

    EditalAmbientalController.$inject = ['Edital'];

    function EditalAmbientalController(Edital) {

        var vm = this;

        vm.editals = [];

        loadAll();

        function loadAll() {
            Edital.query(function(result) {
                vm.editals = result;
                vm.searchQuery = null;
            });
        }
    }
})();
