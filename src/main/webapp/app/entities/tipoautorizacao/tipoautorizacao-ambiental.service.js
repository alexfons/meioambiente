(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Tipoautorizacao', Tipoautorizacao);

    Tipoautorizacao.$inject = ['$resource'];

    function Tipoautorizacao ($resource) {
        var resourceUrl =  'api/tipoautorizacaos/:id';

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
