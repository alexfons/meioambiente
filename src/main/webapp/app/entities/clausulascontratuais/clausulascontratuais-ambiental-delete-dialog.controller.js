(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('ClausulascontratuaisAmbientalDeleteController',ClausulascontratuaisAmbientalDeleteController);

    ClausulascontratuaisAmbientalDeleteController.$inject = ['$uibModalInstance', 'entity', 'Clausulascontratuais'];

    function ClausulascontratuaisAmbientalDeleteController($uibModalInstance, entity, Clausulascontratuais) {
        var vm = this;

        vm.clausulascontratuais = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Clausulascontratuais.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
