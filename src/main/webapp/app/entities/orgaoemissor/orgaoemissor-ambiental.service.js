(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Orgaoemissor', Orgaoemissor);

    Orgaoemissor.$inject = ['$resource'];

    function Orgaoemissor ($resource) {
        var resourceUrl =  'api/orgaoemissors/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
