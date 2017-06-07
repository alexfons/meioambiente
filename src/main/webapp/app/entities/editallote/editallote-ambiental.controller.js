(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('EditalloteAmbientalController', EditalloteAmbientalController);

    EditalloteAmbientalController.$inject = ['Editallote'];

    function EditalloteAmbientalController(Editallote) {

        var vm = this;

        vm.editallotes = [];

        loadAll();

        function loadAll() {
            Editallote.query(function(result) {
                vm.editallotes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
