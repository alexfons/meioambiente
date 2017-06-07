(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('NotificacaoCertifIrregAmbientalController', NotificacaoCertifIrregAmbientalController);

    NotificacaoCertifIrregAmbientalController.$inject = ['NotificacaoCertifIrreg'];

    function NotificacaoCertifIrregAmbientalController(NotificacaoCertifIrreg) {

        var vm = this;

        vm.notificacaoCertifIrregs = [];

        loadAll();

        function loadAll() {
            NotificacaoCertifIrreg.query(function(result) {
                vm.notificacaoCertifIrregs = result;
                vm.searchQuery = null;
            });
        }
    }
})();
