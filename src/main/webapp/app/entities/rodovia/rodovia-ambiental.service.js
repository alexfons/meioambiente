(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Rodovia', Rodovia);

    Rodovia.$inject = ['$resource'];

    function Rodovia ($resource) {
        var resourceUrl =  'api/rodovias/:id';

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
