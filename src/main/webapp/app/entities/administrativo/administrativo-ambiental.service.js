(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Administrativo', Administrativo);

    Administrativo.$inject = ['$resource', 'DateUtils'];

    function Administrativo ($resource, DateUtils) {
        var resourceUrl =  'api/administrativos/:id';

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
