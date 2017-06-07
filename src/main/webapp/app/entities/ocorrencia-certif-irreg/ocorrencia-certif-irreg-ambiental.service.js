(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('OcorrenciaCertifIrreg', OcorrenciaCertifIrreg);

    OcorrenciaCertifIrreg.$inject = ['$resource'];

    function OcorrenciaCertifIrreg ($resource) {
        var resourceUrl =  'api/ocorrencia-certif-irregs/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
