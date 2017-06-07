(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('TipocertifConforAmbientalDeleteController',TipocertifConforAmbientalDeleteController);

    TipocertifConforAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'TipocertifConfor'];

    function TipocertifConforAmbientalDeleteController($uibModalInstance, entity, TipocertifConfor) {
        var vm = this;

        vm.tipocertifConfor = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            TipocertifConfor.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
