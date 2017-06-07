(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('ResponsavelTipo', ResponsavelTipo);

    ResponsavelTipo.$inject = ['$resource'];

    function ResponsavelTipo ($resource) {
        var resourceUrl =  'api/responsavel-tipos/:id';

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
