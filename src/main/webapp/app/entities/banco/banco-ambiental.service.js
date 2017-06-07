(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Banco', Banco);

    Banco.$inject = ['$resource'];

    function Banco ($resource) {
        var resourceUrl =  'api/bancos/:id';

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
