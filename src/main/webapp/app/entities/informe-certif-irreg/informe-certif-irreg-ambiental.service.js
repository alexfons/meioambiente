(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('InformeCertifIrreg', InformeCertifIrreg);

    InformeCertifIrreg.$inject = ['$resource'];

    function InformeCertifIrreg ($resource) {
        var resourceUrl =  'api/informe-certif-irregs/:id';

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
