(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Historico', Historico);

    Historico.$inject = ['$resource', 'DateUtils'];

    function Historico ($resource, DateUtils) {
        var resourceUrl =  'api/historicos/:id';

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
