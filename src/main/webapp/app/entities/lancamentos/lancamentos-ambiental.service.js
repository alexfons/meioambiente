(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Lancamentos', Lancamentos);

    Lancamentos.$inject = ['$resource', 'DateUtils'];

    function Lancamentos ($resource, DateUtils) {
        var resourceUrl =  'api/lancamentos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.datalan = DateUtils.convertDateTimeFromServer(data.datalan);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
