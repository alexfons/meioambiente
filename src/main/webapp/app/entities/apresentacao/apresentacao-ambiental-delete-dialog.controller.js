(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ApresentacaoAmbientalDeleteController',ApresentacaoAmbientalDeleteController);

    ApresentacaoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Apresentacao'];

    function ApresentacaoAmbientalDeleteController($uibModalInstance, entity, Apresentacao) {
        var vm = this;

        vm.apresentacao = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Apresentacao.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
