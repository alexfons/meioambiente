(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Edital', Edital);

    Edital.$inject = ['$resource', 'DateUtils'];

    function Edital ($resource, DateUtils) {
        var resourceUrl =  'api/editals/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dataaprovacao = DateUtils.convertDateTimeFromServer(data.dataaprovacao);
                        data.dataenvio = DateUtils.convertDateTimeFromServer(data.dataenvio);
                        data.datahoraabertura = DateUtils.convertDateTimeFromServer(data.datahoraabertura);
                        data.datalicitacao = DateUtils.convertDateTimeFromServer(data.datalicitacao);
                        data.datanumeromanifestacao = DateUtils.convertDateTimeFromServer(data.datanumeromanifestacao);
                        data.datapublicacao = DateUtils.convertDateTimeFromServer(data.datapublicacao);
                        data.datarelatorio = DateUtils.convertDateTimeFromServer(data.datarelatorio);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
