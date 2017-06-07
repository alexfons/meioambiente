(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Informecertificadoirregularidade', Informecertificadoirregularidade);

    Informecertificadoirregularidade.$inject = ['$resource'];

    function Informecertificadoirregularidade ($resource) {
        var resourceUrl =  'api/informecertificadoirregularidades/:id';

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
