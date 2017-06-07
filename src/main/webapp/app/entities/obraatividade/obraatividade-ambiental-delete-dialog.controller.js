(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ObraatividadeAmbientalDeleteController',ObraatividadeAmbientalDeleteController);

    ObraatividadeAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Obraatividade'];

    function ObraatividadeAmbientalDeleteController($uibModalInstance, entity, Obraatividade) {
        var vm = this;

        vm.obraatividade = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Obraatividade.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
