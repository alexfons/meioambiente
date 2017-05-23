(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Auc', Auc);

    Auc.$inject = ['$resource', 'DateUtils'];

    function Auc ($resource, DateUtils) {
        var resourceUrl =  'api/aucs/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.fceidataprotocolo = DateUtils.convertDateTimeFromServer(data.fceidataprotocolo);
                        data.fceidatapagamento = DateUtils.convertDateTimeFromServer(data.fceidatapagamento);
                        data.dataentregadocs = DateUtils.convertDateTimeFromServer(data.dataentregadocs);
                        data.dataoficiolocalpedido = DateUtils.convertDateTimeFromServer(data.dataoficiolocalpedido);
                        data.dataoficioreoficialpedido = DateUtils.convertDateTimeFromServer(data.dataoficioreoficialpedido);
                        data.dataoficiolocalrecebimento = DateUtils.convertDateTimeFromServer(data.dataoficiolocalrecebimento);
                        data.dataoficioreoficialrecebimento = DateUtils.convertDateTimeFromServer(data.dataoficioreoficialrecebimento);
                        data.dataemissao = DateUtils.convertDateTimeFromServer(data.dataemissao);
                        data.datapedidoprorrogacao1 = DateUtils.convertDateTimeFromServer(data.datapedidoprorrogacao1);
                        data.dataexpedicaoprorrogacao1 = DateUtils.convertDateTimeFromServer(data.dataexpedicaoprorrogacao1);
                        data.datavalidadeprorrogacao1 = DateUtils.convertDateTimeFromServer(data.datavalidadeprorrogacao1);
                        data.datapedidoprorrogacao2 = DateUtils.convertDateTimeFromServer(data.datapedidoprorrogacao2);
                        data.dataexpedicaoprorrogacao2 = DateUtils.convertDateTimeFromServer(data.dataexpedicaoprorrogacao2);
                        data.datavalidadeprorrogacao2 = DateUtils.convertDateTimeFromServer(data.datavalidadeprorrogacao2);
                        data.datapedidoprorrogacao3 = DateUtils.convertDateTimeFromServer(data.datapedidoprorrogacao3);
                        data.dataexpedicaoprorrogacao3 = DateUtils.convertDateTimeFromServer(data.dataexpedicaoprorrogacao3);
                        data.datavalidadeprorrogacao3 = DateUtils.convertDateTimeFromServer(data.datavalidadeprorrogacao3);
                        data.datavencimento = DateUtils.convertDateTimeFromServer(data.datavencimento);
                        data.datavencimentoatual = DateUtils.convertDateTimeFromServer(data.datavencimentoatual);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
