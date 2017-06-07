(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('NotificacaoAmbientalController', NotificacaoAmbientalController);

    NotificacaoAmbientalController.$inject = ['Notificacao'];

    function NotificacaoAmbientalController(Notificacao) {

        var vm = this;

        vm.notificacaos = [];

        loadAll();

        function loadAll() {
            Notificacao.query(function(result) {
                vm.notificacaos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
