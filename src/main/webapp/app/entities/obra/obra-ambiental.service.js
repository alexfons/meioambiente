(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Obra', Obra);

    Obra.$inject = ['$resource'];

    function Obra ($resource) {
        var resourceUrl =  'api/obras/:id';

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
