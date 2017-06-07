(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Pendencias', Pendencias);

    Pendencias.$inject = ['$resource', 'DateUtils'];

    function Pendencias ($resource, DateUtils) {
        var resourceUrl =  'api/pendencias/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.data = DateUtils.convertDateTimeFromServer(data.data);
                        data.datainspecao = DateUtils.convertDateTimeFromServer(data.datainspecao);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
