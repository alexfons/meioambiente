(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('CustosconcorrentesAmbientalDeleteController',CustosconcorrentesAmbientalDeleteController);

    CustosconcorrentesAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Custosconcorrentes'];

    function CustosconcorrentesAmbientalDeleteController($uibModalInstance, entity, Custosconcorrentes) {
        var vm = this;

        vm.custosconcorrentes = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Custosconcorrentes.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
