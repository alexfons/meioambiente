(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Apresentacao', Apresentacao);

    Apresentacao.$inject = ['$resource', 'DateUtils'];

    function Apresentacao ($resource, DateUtils) {
        var resourceUrl =  'api/apresentacaos/:id';

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
