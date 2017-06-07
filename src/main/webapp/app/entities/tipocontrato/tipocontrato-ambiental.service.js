(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Tipocontrato', Tipocontrato);

    Tipocontrato.$inject = ['$resource'];

    function Tipocontrato ($resource) {
        var resourceUrl =  'api/tipocontratoes/:id';

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
