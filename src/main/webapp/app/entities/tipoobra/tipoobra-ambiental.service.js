(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Tipoobra', Tipoobra);

    Tipoobra.$inject = ['$resource'];

    function Tipoobra ($resource) {
        var resourceUrl =  'api/tipoobras/:id';

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
