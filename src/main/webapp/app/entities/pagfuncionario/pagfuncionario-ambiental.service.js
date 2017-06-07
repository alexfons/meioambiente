(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Pagfuncionario', Pagfuncionario);

    Pagfuncionario.$inject = ['$resource', 'DateUtils'];

    function Pagfuncionario ($resource, DateUtils) {
        var resourceUrl =  'api/pagfuncionarios/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.datapagamento = DateUtils.convertDateTimeFromServer(data.datapagamento);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
