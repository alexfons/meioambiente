(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Planoaquisicoes', Planoaquisicoes);

    Planoaquisicoes.$inject = ['$resource', 'DateUtils'];

    function Planoaquisicoes ($resource, DateUtils) {
        var resourceUrl =  'api/planoaquisicoes/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.avisolicitacao = DateUtils.convertDateTimeFromServer(data.avisolicitacao);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
