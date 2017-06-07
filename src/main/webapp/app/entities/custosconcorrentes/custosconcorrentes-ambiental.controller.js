(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CustosconcorrentesAmbientalController', CustosconcorrentesAmbientalController);

    CustosconcorrentesAmbientalController.$inject = ['Custosconcorrentes'];

    function CustosconcorrentesAmbientalController(Custosconcorrentes) {

        var vm = this;

        vm.custosconcorrentes = [];

        loadAll();

        function loadAll() {
            Custosconcorrentes.query(function(result) {
                vm.custosconcorrentes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
