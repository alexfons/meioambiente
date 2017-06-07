(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Tipocertificadoconformidade', Tipocertificadoconformidade);

    Tipocertificadoconformidade.$inject = ['$resource'];

    function Tipocertificadoconformidade ($resource) {
        var resourceUrl =  'api/tipocertificadoconformidades/:id';

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
