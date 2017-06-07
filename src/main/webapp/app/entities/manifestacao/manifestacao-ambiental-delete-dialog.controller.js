(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ManifestacaoAmbientalDeleteController',ManifestacaoAmbientalDeleteController);

    ManifestacaoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Manifestacao'];

    function ManifestacaoAmbientalDeleteController($uibModalInstance, entity, Manifestacao) {
        var vm = this;

        vm.manifestacao = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Manifestacao.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
