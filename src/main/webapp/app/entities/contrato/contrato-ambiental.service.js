(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Contrato', Contrato);

    Contrato.$inject = ['$resource', 'DateUtils'];

    function Contrato ($resource, DateUtils) {
        var resourceUrl =  'api/contratoes/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dataatual = DateUtils.convertDateTimeFromServer(data.dataatual);
                        data.databasecontrato = DateUtils.convertDateTimeFromServer(data.databasecontrato);
                        data.datacontratacao = DateUtils.convertDateTimeFromServer(data.datacontratacao);
                        data.dataterminocaucao = DateUtils.convertDateTimeFromServer(data.dataterminocaucao);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
