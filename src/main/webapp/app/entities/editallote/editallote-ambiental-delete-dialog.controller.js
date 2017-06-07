(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('EditalloteAmbientalDeleteController',EditalloteAmbientalDeleteController);

    EditalloteAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Editallote'];

    function EditalloteAmbientalDeleteController($uibModalInstance, entity, Editallote) {
        var vm = this;

        vm.editallote = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Editallote.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
