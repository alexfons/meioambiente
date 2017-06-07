(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoautorizacaoAmbientalController', TipoautorizacaoAmbientalController);

    TipoautorizacaoAmbientalController.$inject = ['Tipoautorizacao'];

    function TipoautorizacaoAmbientalController(Tipoautorizacao) {

        var vm = this;

        vm.tipoautorizacaos = [];

        loadAll();

        function loadAll() {
            Tipoautorizacao.query(function(result) {
                vm.tipoautorizacaos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
