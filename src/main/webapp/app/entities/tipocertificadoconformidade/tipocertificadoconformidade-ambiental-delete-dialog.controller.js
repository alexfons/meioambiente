(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipocertificadoconformidadeAmbientalDeleteController',TipocertificadoconformidadeAmbientalDeleteController);

    TipocertificadoconformidadeAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Tipocertificadoconformidade'];

    function TipocertificadoconformidadeAmbientalDeleteController($uibModalInstance, entity, Tipocertificadoconformidade) {
        var vm = this;

        vm.tipocertificadoconformidade = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Tipocertificadoconformidade.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
