(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ScriptAmbientalController', ScriptAmbientalController);

    ScriptAmbientalController.$inject = ['Script'];

    function ScriptAmbientalController(Script) {

        var vm = this;

        vm.scripts = [];

        loadAll();

        function loadAll() {
            Script.query(function(result) {
                vm.scripts = result;
                vm.searchQuery = null;
            });
        }
    }
})();
