(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Fatura', Fatura);

    Fatura.$inject = ['$resource', 'DateUtils'];

    function Fatura ($resource, DateUtils) {
        var resourceUrl =  'api/faturas/:id';

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
