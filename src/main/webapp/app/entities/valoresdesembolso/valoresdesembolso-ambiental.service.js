(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Valoresdesembolso', Valoresdesembolso);

    Valoresdesembolso.$inject = ['$resource', 'DateUtils'];

    function Valoresdesembolso ($resource, DateUtils) {
        var resourceUrl =  'api/valoresdesembolsos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.datainternalizacao = DateUtils.convertDateTimeFromServer(data.datainternalizacao);
                        data.valuedata = DateUtils.convertDateTimeFromServer(data.valuedata);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
