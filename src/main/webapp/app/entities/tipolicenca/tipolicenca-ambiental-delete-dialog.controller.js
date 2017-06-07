(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipolicencaAmbientalDeleteController',TipolicencaAmbientalDeleteController);

    TipolicencaAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Tipolicenca'];

    function TipolicencaAmbientalDeleteController($uibModalInstance, entity, Tipolicenca) {
        var vm = this;

        vm.tipolicenca = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Tipolicenca.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
