(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Condicionante', Condicionante);

    Condicionante.$inject = ['$resource', 'DateUtils'];

    function Condicionante ($resource, DateUtils) {
        var resourceUrl =  'api/condicionantes/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.data = DateUtils.convertDateTimeFromServer(data.data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
