(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OcorrenciaCertifIrregAmbientalController', OcorrenciaCertifIrregAmbientalController);

    OcorrenciaCertifIrregAmbientalController.$inject = ['OcorrenciaCertifIrreg'];

    function OcorrenciaCertifIrregAmbientalController(OcorrenciaCertifIrreg) {

        var vm = this;

        vm.ocorrenciaCertifIrregs = [];

        loadAll();

        function loadAll() {
            OcorrenciaCertifIrreg.query(function(result) {
                vm.ocorrenciaCertifIrregs = result;
                vm.searchQuery = null;
            });
        }
    }
})();
