(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Solicitacao', Solicitacao);

    Solicitacao.$inject = ['$resource', 'DateUtils'];

    function Solicitacao ($resource, DateUtils) {
        var resourceUrl =  'api/solicitacaos/:id';

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
