(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FisicograficoAmbientalDeleteController',FisicograficoAmbientalDeleteController);

    FisicograficoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Fisicografico'];

    function FisicograficoAmbientalDeleteController($uibModalInstance, entity, Fisicografico) {
        var vm = this;

        vm.fisicografico = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Fisicografico.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
