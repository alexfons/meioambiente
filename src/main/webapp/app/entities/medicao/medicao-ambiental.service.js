(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Medicao', Medicao);

    Medicao.$inject = ['$resource', 'DateUtils'];

    function Medicao ($resource, DateUtils) {
        var resourceUrl =  'api/medicaos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.mes = DateUtils.convertDateTimeFromServer(data.mes);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
