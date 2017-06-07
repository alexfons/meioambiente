(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Ocorrenciacertificadoirregularidade', Ocorrenciacertificadoirregularidade);

    Ocorrenciacertificadoirregularidade.$inject = ['$resource'];

    function Ocorrenciacertificadoirregularidade ($resource) {
        var resourceUrl =  'api/ocorrenciacertificadoirregularidades/:id';

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
