(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('TipoObra', TipoObra);

    TipoObra.$inject = ['$resource'];

    function TipoObra ($resource) {
        var resourceUrl =  'api/tipo-obras/:id';

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
