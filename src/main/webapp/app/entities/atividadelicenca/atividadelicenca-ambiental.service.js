(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Atividadelicenca', Atividadelicenca);

    Atividadelicenca.$inject = ['$resource'];

    function Atividadelicenca ($resource) {
        var resourceUrl =  'api/atividadelicencas/:id';

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
