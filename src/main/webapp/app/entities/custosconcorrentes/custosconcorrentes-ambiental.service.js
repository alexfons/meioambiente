(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Custosconcorrentes', Custosconcorrentes);

    Custosconcorrentes.$inject = ['$resource', 'DateUtils'];

    function Custosconcorrentes ($resource, DateUtils) {
        var resourceUrl =  'api/custosconcorrentes/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.datainicio = DateUtils.convertDateTimeFromServer(data.datainicio);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
