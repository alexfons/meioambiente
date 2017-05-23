(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ProjetoAmbientalDeleteController',ProjetoAmbientalDeleteController);

    ProjetoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Projeto'];

    function ProjetoAmbientalDeleteController($uibModalInstance, entity, Projeto) {
        var vm = this;

        vm.projeto = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Projeto.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
