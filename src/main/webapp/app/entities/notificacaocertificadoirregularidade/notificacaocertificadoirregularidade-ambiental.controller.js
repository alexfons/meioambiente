(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('NotificacaocertificadoirregularidadeAmbientalController', NotificacaocertificadoirregularidadeAmbientalController);

    NotificacaocertificadoirregularidadeAmbientalController.$inject = ['Notificacaocertificadoirregularidade'];

    function NotificacaocertificadoirregularidadeAmbientalController(Notificacaocertificadoirregularidade) {

        var vm = this;

        vm.notificacaocertificadoirregularidades = [];

        loadAll();

        function loadAll() {
            Notificacaocertificadoirregularidade.query(function(result) {
                vm.notificacaocertificadoirregularidades = result;
                vm.searchQuery = null;
            });
        }
    }
})();
