(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('DocumentoAmbientalController', DocumentoAmbientalController);

    DocumentoAmbientalController.$inject = ['Documento'];

    function DocumentoAmbientalController(Documento) {

        var vm = this;

        vm.documentos = [];

        loadAll();

        function loadAll() {
            Documento.query(function(result) {
                vm.documentos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
