(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Referenciacontrato', Referenciacontrato);

    Referenciacontrato.$inject = ['$resource'];

    function Referenciacontrato ($resource) {
        var resourceUrl =  'api/referenciacontratoes/:id';

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
