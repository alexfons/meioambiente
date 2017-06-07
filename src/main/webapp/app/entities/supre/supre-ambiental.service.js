(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Supre', Supre);

    Supre.$inject = ['$resource'];

    function Supre ($resource) {
        var resourceUrl =  'api/supres/:id';

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
