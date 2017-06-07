(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('UsuarioAmbientalController', UsuarioAmbientalController);

    UsuarioAmbientalController.$inject = ['Usuario'];

    function UsuarioAmbientalController(Usuario) {

        var vm = this;

        vm.usuarios = [];

        loadAll();

        function loadAll() {
            Usuario.query(function(result) {
                vm.usuarios = result;
                vm.searchQuery = null;
            });
        }
    }
})();
