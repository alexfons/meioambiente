(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Tipoadministrativo', Tipoadministrativo);

    Tipoadministrativo.$inject = ['$resource'];

    function Tipoadministrativo ($resource) {
        var resourceUrl =  'api/tipoadministrativos/:id';

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
