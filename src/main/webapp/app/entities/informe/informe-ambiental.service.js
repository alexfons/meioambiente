(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Informe', Informe);

    Informe.$inject = ['$resource', 'DateUtils'];

    function Informe ($resource, DateUtils) {
        var resourceUrl =  'api/informes/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.data = DateUtils.convertDateTimeFromServer(data.data);
                        data.datainspecao = DateUtils.convertDateTimeFromServer(data.datainspecao);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
