(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Empresacontrato', Empresacontrato);

    Empresacontrato.$inject = ['$resource'];

    function Empresacontrato ($resource) {
        var resourceUrl =  'api/empresacontratoes/:id';

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
