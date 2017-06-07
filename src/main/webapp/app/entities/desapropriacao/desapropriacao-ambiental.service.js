(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Desapropriacao', Desapropriacao);

    Desapropriacao.$inject = ['$resource', 'DateUtils'];

    function Desapropriacao ($resource, DateUtils) {
        var resourceUrl =  'api/desapropriacaos/:id';

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
