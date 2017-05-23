(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('OrgaoemissorAmbientalController', OrgaoemissorAmbientalController);

    OrgaoemissorAmbientalController.$inject = ['Orgaoemissor'];

    function OrgaoemissorAmbientalController(Orgaoemissor) {

        var vm = this;

        vm.orgaoemissors = [];

        loadAll();

        function loadAll() {
            Orgaoemissor.query(function(result) {
                vm.orgaoemissors = result;
                vm.searchQuery = null;
            });
        }
    }
})();
