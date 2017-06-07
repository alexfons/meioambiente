(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoautorizacaoAmbientalDeleteController',TipoautorizacaoAmbientalDeleteController);

    TipoautorizacaoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Tipoautorizacao'];

    function TipoautorizacaoAmbientalDeleteController($uibModalInstance, entity, Tipoautorizacao) {
        var vm = this;

        vm.tipoautorizacao = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Tipoautorizacao.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
