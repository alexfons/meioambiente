(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Obrafisicomensal', Obrafisicomensal);

    Obrafisicomensal.$inject = ['$resource', 'DateUtils'];

    function Obrafisicomensal ($resource, DateUtils) {
        var resourceUrl =  'api/obrafisicomensals/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.datainspecao = DateUtils.convertDateTimeFromServer(data.datainspecao);
                        data.datarelatorio = DateUtils.convertDateTimeFromServer(data.datarelatorio);
                        data.previsaoatual = DateUtils.convertDateTimeFromServer(data.previsaoatual);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
