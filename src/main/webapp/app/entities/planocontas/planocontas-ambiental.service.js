(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Planocontas', Planocontas);

    Planocontas.$inject = ['$resource'];

    function Planocontas ($resource) {
        var resourceUrl =  'api/planocontas/:id';

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
