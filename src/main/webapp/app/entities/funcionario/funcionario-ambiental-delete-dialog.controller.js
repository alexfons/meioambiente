(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FuncionarioAmbientalDeleteController',FuncionarioAmbientalDeleteController);

    FuncionarioAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Funcionario'];

    function FuncionarioAmbientalDeleteController($uibModalInstance, entity, Funcionario) {
        var vm = this;

        vm.funcionario = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Funcionario.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
