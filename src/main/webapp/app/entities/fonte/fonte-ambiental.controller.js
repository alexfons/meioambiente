(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FonteAmbientalController', FonteAmbientalController);

    FonteAmbientalController.$inject = ['Fonte'];

    function FonteAmbientalController(Fonte) {

        var vm = this;

        vm.fontes = [];

        loadAll();

        function loadAll() {
            Fonte.query(function(result) {
                vm.fontes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
