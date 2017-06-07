(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Tipoocorrencia', Tipoocorrencia);

    Tipoocorrencia.$inject = ['$resource'];

    function Tipoocorrencia ($resource) {
        var resourceUrl =  'api/tipoocorrencias/:id';

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
