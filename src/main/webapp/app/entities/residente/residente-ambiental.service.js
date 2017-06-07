(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Residente', Residente);

    Residente.$inject = ['$resource'];

    function Residente ($resource) {
        var resourceUrl =  'api/residentes/:id';

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
