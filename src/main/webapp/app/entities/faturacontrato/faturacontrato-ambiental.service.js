(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Faturacontrato', Faturacontrato);

    Faturacontrato.$inject = ['$resource', 'DateUtils'];

    function Faturacontrato ($resource, DateUtils) {
        var resourceUrl =  'api/faturacontratoes/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dataop = DateUtils.convertDateTimeFromServer(data.dataop);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
