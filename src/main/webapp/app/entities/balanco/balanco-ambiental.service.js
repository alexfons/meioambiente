(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Balanco', Balanco);

    Balanco.$inject = ['$resource', 'DateUtils'];

    function Balanco ($resource, DateUtils) {
        var resourceUrl =  'api/balancos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.datafim = DateUtils.convertDateTimeFromServer(data.datafim);
                        data.datainicio = DateUtils.convertDateTimeFromServer(data.datainicio);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
