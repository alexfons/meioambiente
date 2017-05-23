(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Aditivocontrato', Aditivocontrato);

    Aditivocontrato.$inject = ['$resource', 'DateUtils'];

    function Aditivocontrato ($resource, DateUtils) {
        var resourceUrl =  'api/aditivocontratoes/:id';

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
