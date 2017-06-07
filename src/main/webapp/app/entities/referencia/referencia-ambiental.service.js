(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Referencia', Referencia);

    Referencia.$inject = ['$resource'];

    function Referencia ($resource) {
        var resourceUrl =  'api/referencias/:id';

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
