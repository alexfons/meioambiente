(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Categoriainversao', Categoriainversao);

    Categoriainversao.$inject = ['$resource'];

    function Categoriainversao ($resource) {
        var resourceUrl =  'api/categoriainversaos/:id';

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
