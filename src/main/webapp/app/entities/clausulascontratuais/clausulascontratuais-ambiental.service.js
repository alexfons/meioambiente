(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Clausulascontratuais', Clausulascontratuais);

    Clausulascontratuais.$inject = ['$resource', 'DateUtils'];

    function Clausulascontratuais ($resource, DateUtils) {
        var resourceUrl =  'api/clausulascontratuais/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dataaprovacao = DateUtils.convertDateTimeFromServer(data.dataaprovacao);
                        data.dataenvio = DateUtils.convertDateTimeFromServer(data.dataenvio);
                        data.datavigente = DateUtils.convertDateTimeFromServer(data.datavigente);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
