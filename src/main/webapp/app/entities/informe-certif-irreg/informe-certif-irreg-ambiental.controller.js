(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('InformeCertifIrregAmbientalController', InformeCertifIrregAmbientalController);

    InformeCertifIrregAmbientalController.$inject = ['InformeCertifIrreg'];

    function InformeCertifIrregAmbientalController(InformeCertifIrreg) {

        var vm = this;

        vm.informeCertifIrregs = [];

        loadAll();

        function loadAll() {
            InformeCertifIrreg.query(function(result) {
                vm.informeCertifIrregs = result;
                vm.searchQuery = null;
            });
        }
    }
})();
