(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Tabela', Tabela);

    Tabela.$inject = ['$resource'];

    function Tabela ($resource) {
        var resourceUrl =  'api/tabelas/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
