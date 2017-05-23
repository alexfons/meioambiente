(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Condicionante', Condicionante);

    Condicionante.$inject = ['$resource'];

    function Condicionante ($resource) {
        var resourceUrl =  'api/condicionantes/:id';

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
