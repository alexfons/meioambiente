(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('InformeAmbientalDeleteController',InformeAmbientalDeleteController);

    InformeAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Informe'];

    function InformeAmbientalDeleteController($uibModalInstance, entity, Informe) {
        var vm = this;

        vm.informe = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Informe.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
