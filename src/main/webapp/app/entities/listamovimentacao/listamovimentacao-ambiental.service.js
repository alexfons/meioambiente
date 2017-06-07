(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Listamovimentacao', Listamovimentacao);

    Listamovimentacao.$inject = ['$resource'];

    function Listamovimentacao ($resource) {
        var resourceUrl =  'api/listamovimentacaos/:id';

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
