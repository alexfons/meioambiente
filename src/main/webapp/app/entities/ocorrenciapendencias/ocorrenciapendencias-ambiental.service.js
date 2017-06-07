(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Ocorrenciapendencias', Ocorrenciapendencias);

    Ocorrenciapendencias.$inject = ['$resource'];

    function Ocorrenciapendencias ($resource) {
        var resourceUrl =  'api/ocorrenciapendencias/:id';

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
