(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Responsavel', Responsavel);

    Responsavel.$inject = ['$resource'];

    function Responsavel ($resource) {
        var resourceUrl =  'api/responsavels/:id';

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
