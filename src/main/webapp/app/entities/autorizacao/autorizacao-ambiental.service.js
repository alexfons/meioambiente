(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Autorizacao', Autorizacao);

    Autorizacao.$inject = ['$resource', 'DateUtils'];

    function Autorizacao ($resource, DateUtils) {
        var resourceUrl =  'api/autorizacaos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.data = DateUtils.convertDateTimeFromServer(data.data);
                        data.dataentregadocs = DateUtils.convertDateTimeFromServer(data.dataentregadocs);
                        data.dataexpedicaoprorrogacao1 = DateUtils.convertDateTimeFromServer(data.dataexpedicaoprorrogacao1);
                        data.dataexpedicaoprorrogacao2 = DateUtils.convertDateTimeFromServer(data.dataexpedicaoprorrogacao2);
                        data.dataexpedicaoprorrogacao3 = DateUtils.convertDateTimeFromServer(data.dataexpedicaoprorrogacao3);
                        data.datapedidoprorrogacao1 = DateUtils.convertDateTimeFromServer(data.datapedidoprorrogacao1);
                        data.datapedidoprorrogacao2 = DateUtils.convertDateTimeFromServer(data.datapedidoprorrogacao2);
                        data.datapedidoprorrogacao3 = DateUtils.convertDateTimeFromServer(data.datapedidoprorrogacao3);
                        data.datavalidadeprorrogacao1 = DateUtils.convertDateTimeFromServer(data.datavalidadeprorrogacao1);
                        data.datavalidadeprorrogacao2 = DateUtils.convertDateTimeFromServer(data.datavalidadeprorrogacao2);
                        data.datavalidadeprorrogacao3 = DateUtils.convertDateTimeFromServer(data.datavalidadeprorrogacao3);
                        data.datavencimento = DateUtils.convertDateTimeFromServer(data.datavencimento);
                        data.datavencimentoatual = DateUtils.convertDateTimeFromServer(data.datavencimentoatual);
                        data.fceidatapagamento = DateUtils.convertDateTimeFromServer(data.fceidatapagamento);
                        data.fceidataprotocolo = DateUtils.convertDateTimeFromServer(data.fceidataprotocolo);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
