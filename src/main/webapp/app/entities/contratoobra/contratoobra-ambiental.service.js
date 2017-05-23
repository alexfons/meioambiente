(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Contratoobra', Contratoobra);

    Contratoobra.$inject = ['$resource'];

    function Contratoobra ($resource) {
        var resourceUrl =  'api/contratoobras/:id';

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
