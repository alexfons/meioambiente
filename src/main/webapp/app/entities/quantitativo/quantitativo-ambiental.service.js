(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Quantitativo', Quantitativo);

    Quantitativo.$inject = ['$resource'];

    function Quantitativo ($resource) {
        var resourceUrl =  'api/quantitativos/:id';

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
