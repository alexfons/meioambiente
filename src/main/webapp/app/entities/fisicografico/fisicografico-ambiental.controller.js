(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .controller('FisicograficoAmbientalController', FisicograficoAmbientalController);

    FisicograficoAmbientalController.$inject = ['Fisicografico'];

    function FisicograficoAmbientalController(Fisicografico) {

        var vm = this;

        vm.fisicograficos = [];

        loadAll();

        function loadAll() {
            Fisicografico.query(function(result) {
                vm.fisicograficos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
