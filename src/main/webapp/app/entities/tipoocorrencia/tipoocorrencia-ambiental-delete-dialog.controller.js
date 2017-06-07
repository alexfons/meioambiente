(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipoocorrenciaAmbientalDeleteController',TipoocorrenciaAmbientalDeleteController);

    TipoocorrenciaAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Tipoocorrencia'];

    function TipoocorrenciaAmbientalDeleteController($uibModalInstance, entity, Tipoocorrencia) {
        var vm = this;

        vm.tipoocorrencia = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Tipoocorrencia.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
