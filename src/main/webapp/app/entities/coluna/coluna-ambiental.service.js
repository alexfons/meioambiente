(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Coluna', Coluna);

    Coluna.$inject = ['$resource'];

    function Coluna ($resource) {
        var resourceUrl =  'api/colunas/:id';

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
