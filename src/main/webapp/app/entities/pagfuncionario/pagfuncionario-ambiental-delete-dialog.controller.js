(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('PagfuncionarioAmbientalDeleteController',PagfuncionarioAmbientalDeleteController);

    PagfuncionarioAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Pagfuncionario'];

    function PagfuncionarioAmbientalDeleteController($uibModalInstance, entity, Pagfuncionario) {
        var vm = this;

        vm.pagfuncionario = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Pagfuncionario.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
