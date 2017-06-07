(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Ocorrencia', Ocorrencia);

    Ocorrencia.$inject = ['$resource', 'DateUtils'];

    function Ocorrencia ($resource, DateUtils) {
        var resourceUrl =  'api/ocorrencias/:id';

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
