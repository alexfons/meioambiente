(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipocertifConforAmbientalController', TipocertifConforAmbientalController);

    TipocertifConforAmbientalController.$inject = ['TipocertifConfor'];

    function TipocertifConforAmbientalController(TipocertifConfor) {

        var vm = this;

        vm.tipocertifConfors = [];

        loadAll();

        function loadAll() {
            TipocertifConfor.query(function(result) {
                vm.tipocertifConfors = result;
                vm.searchQuery = null;
            });
        }
    }
})();
