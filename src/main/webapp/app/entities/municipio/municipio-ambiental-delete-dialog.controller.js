(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('MunicipioAmbientalDeleteController',MunicipioAmbientalDeleteController);

    MunicipioAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Municipio'];

    function MunicipioAmbientalDeleteController($uibModalInstance, entity, Municipio) {
        var vm = this;

        vm.municipio = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Municipio.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
