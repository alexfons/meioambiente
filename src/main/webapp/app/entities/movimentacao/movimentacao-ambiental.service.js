(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Movimentacao', Movimentacao);

    Movimentacao.$inject = ['$resource', 'DateUtils'];

    function Movimentacao ($resource, DateUtils) {
        var resourceUrl =  'api/movimentacaos/:id';

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
