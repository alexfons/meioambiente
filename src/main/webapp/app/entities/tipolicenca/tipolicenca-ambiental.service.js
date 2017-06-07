(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Tipolicenca', Tipolicenca);

    Tipolicenca.$inject = ['$resource'];

    function Tipolicenca ($resource) {
        var resourceUrl =  'api/tipolicencas/:id';

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
