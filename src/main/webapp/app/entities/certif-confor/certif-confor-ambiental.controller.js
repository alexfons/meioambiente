(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CertifConforAmbientalController', CertifConforAmbientalController);

    CertifConforAmbientalController.$inject = ['CertifConfor'];

    function CertifConforAmbientalController(CertifConfor) {

        var vm = this;

        vm.certifConfors = [];

        loadAll();

        function loadAll() {
            CertifConfor.query(function(result) {
                vm.certifConfors = result;
                vm.searchQuery = null;
            });
        }
    }
})();
