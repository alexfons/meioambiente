(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipolicencaAmbientalController', TipolicencaAmbientalController);

    TipolicencaAmbientalController.$inject = ['Tipolicenca'];

    function TipolicencaAmbientalController(Tipolicenca) {

        var vm = this;

        vm.tipolicencas = [];

        loadAll();

        function loadAll() {
            Tipolicenca.query(function(result) {
                vm.tipolicencas = result;
                vm.searchQuery = null;
            });
        }
    }
})();
