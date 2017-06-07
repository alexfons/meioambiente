(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Natureza', Natureza);

    Natureza.$inject = ['$resource'];

    function Natureza ($resource) {
        var resourceUrl =  'api/naturezas/:id';

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
