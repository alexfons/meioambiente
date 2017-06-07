(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Registro', Registro);

    Registro.$inject = ['$resource'];

    function Registro ($resource) {
        var resourceUrl =  'api/registros/:id';

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
