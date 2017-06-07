(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ClausulascontratuaisAmbientalController', ClausulascontratuaisAmbientalController);

    ClausulascontratuaisAmbientalController.$inject = ['Clausulascontratuais'];

    function ClausulascontratuaisAmbientalController(Clausulascontratuais) {

        var vm = this;

        vm.clausulascontratuais = [];

        loadAll();

        function loadAll() {
            Clausulascontratuais.query(function(result) {
                vm.clausulascontratuais = result;
                vm.searchQuery = null;
            });
        }
    }
})();
