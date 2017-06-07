(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Contratoprojeto', Contratoprojeto);

    Contratoprojeto.$inject = ['$resource'];

    function Contratoprojeto ($resource) {
        var resourceUrl =  'api/contratoprojetos/:id';

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
