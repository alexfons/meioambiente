(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Manifestacao', Manifestacao);

    Manifestacao.$inject = ['$resource', 'DateUtils'];

    function Manifestacao ($resource, DateUtils) {
        var resourceUrl =  'api/manifestacaos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dataaviso = DateUtils.convertDateTimeFromServer(data.dataaviso);
                        data.dataentrega = DateUtils.convertDateTimeFromServer(data.dataentrega);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
