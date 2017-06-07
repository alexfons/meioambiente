(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Ocorrenciainforme', Ocorrenciainforme);

    Ocorrenciainforme.$inject = ['$resource'];

    function Ocorrenciainforme ($resource) {
        var resourceUrl =  'api/ocorrenciainformes/:id';

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
