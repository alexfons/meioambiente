(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Inspetor', Inspetor);

    Inspetor.$inject = ['$resource'];

    function Inspetor ($resource) {
        var resourceUrl =  'api/inspetors/:id';

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
