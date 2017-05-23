(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('AtividadeAmbientalDeleteController',AtividadeAmbientalDeleteController);

    AtividadeAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Atividade'];

    function AtividadeAmbientalDeleteController($uibModalInstance, entity, Atividade) {
        var vm = this;

        vm.atividade = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Atividade.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
