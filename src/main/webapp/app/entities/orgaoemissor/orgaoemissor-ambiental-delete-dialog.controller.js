(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OrgaoemissorAmbientalDeleteController',OrgaoemissorAmbientalDeleteController);

    OrgaoemissorAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Orgaoemissor'];

    function OrgaoemissorAmbientalDeleteController($uibModalInstance, entity, Orgaoemissor) {
        var vm = this;

        vm.orgaoemissor = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Orgaoemissor.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
