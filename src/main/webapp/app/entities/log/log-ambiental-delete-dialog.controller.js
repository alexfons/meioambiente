(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('LogAmbientalDeleteController',LogAmbientalDeleteController);

    LogAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Log'];

    function LogAmbientalDeleteController($uibModalInstance, entity, Log) {
        var vm = this;

        vm.log = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Log.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
