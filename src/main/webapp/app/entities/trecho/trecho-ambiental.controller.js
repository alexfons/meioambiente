(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TrechoAmbientalController', TrechoAmbientalController);

    TrechoAmbientalController.$inject = ['Trecho'];

    function TrechoAmbientalController(Trecho) {

        var vm = this;

        vm.trechos = [];

        loadAll();

        function loadAll() {
            Trecho.query(function(result) {
                vm.trechos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
