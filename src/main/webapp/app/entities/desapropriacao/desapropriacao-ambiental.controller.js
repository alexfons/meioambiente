(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('DesapropriacaoAmbientalController', DesapropriacaoAmbientalController);

    DesapropriacaoAmbientalController.$inject = ['Desapropriacao'];

    function DesapropriacaoAmbientalController(Desapropriacao) {

        var vm = this;

        vm.desapropriacaos = [];

        loadAll();

        function loadAll() {
            Desapropriacao.query(function(result) {
                vm.desapropriacaos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
