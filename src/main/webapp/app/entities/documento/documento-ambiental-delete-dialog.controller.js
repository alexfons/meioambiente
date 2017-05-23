(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('DocumentoAmbientalDeleteController',DocumentoAmbientalDeleteController);

    DocumentoAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Documento'];

    function DocumentoAmbientalDeleteController($uibModalInstance, entity, Documento) {
        var vm = this;

        vm.documento = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Documento.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
