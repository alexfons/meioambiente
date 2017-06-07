(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('LinhaAmbientalController', LinhaAmbientalController);

    LinhaAmbientalController.$inject = ['Linha'];

    function LinhaAmbientalController(Linha) {

        var vm = this;

        vm.linhas = [];

        loadAll();

        function loadAll() {
            Linha.query(function(result) {
                vm.linhas = result;
                vm.searchQuery = null;
            });
        }
    }
})();
