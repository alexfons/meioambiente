(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CategoriainversaoAmbientalDeleteController',CategoriainversaoAmbientalDeleteController);

    CategoriainversaoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Categoriainversao'];

    function CategoriainversaoAmbientalDeleteController($uibModalInstance, entity, Categoriainversao) {
        var vm = this;

        vm.categoriainversao = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Categoriainversao.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
