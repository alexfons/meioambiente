(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Contratoagente', Contratoagente);

    Contratoagente.$inject = ['$resource', 'DateUtils'];

    function Contratoagente ($resource, DateUtils) {
        var resourceUrl =  'api/contratoagentes/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dataaprovacao = DateUtils.convertDateTimeFromServer(data.dataaprovacao);
                        data.dataassinatura = DateUtils.convertDateTimeFromServer(data.dataassinatura);
                        data.dataconclusao = DateUtils.convertDateTimeFromServer(data.dataconclusao);
                        data.datainicio = DateUtils.convertDateTimeFromServer(data.datainicio);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
