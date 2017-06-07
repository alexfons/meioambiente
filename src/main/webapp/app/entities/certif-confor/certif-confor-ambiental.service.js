(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('CertifConfor', CertifConfor);

    CertifConfor.$inject = ['$resource', 'DateUtils'];

    function CertifConfor ($resource, DateUtils) {
        var resourceUrl =  'api/certif-confors/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.data = DateUtils.convertDateTimeFromServer(data.data);
                        data.dataliberacao = DateUtils.convertDateTimeFromServer(data.dataliberacao);
                        data.dataparalisacao = DateUtils.convertDateTimeFromServer(data.dataparalisacao);
                        data.datareinicio = DateUtils.convertDateTimeFromServer(data.datareinicio);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
