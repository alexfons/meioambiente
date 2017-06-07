(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Editallote', Editallote);

    Editallote.$inject = ['$resource', 'DateUtils'];

    function Editallote ($resource, DateUtils) {
        var resourceUrl =  'api/editallotes/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dataprovacaolote = DateUtils.convertDateTimeFromServer(data.dataprovacaolote);
                        data.datarelatoriolote = DateUtils.convertDateTimeFromServer(data.datarelatoriolote);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
