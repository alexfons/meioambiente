(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Notificacao', Notificacao);

    Notificacao.$inject = ['$resource', 'DateUtils'];

    function Notificacao ($resource, DateUtils) {
        var resourceUrl =  'api/notificacaos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.data = DateUtils.convertDateTimeFromServer(data.data);
                        data.datainspecao = DateUtils.convertDateTimeFromServer(data.datainspecao);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
