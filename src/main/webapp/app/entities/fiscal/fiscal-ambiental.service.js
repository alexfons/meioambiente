(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Fiscal', Fiscal);

    Fiscal.$inject = ['$resource'];

    function Fiscal ($resource) {
        var resourceUrl =  'api/fiscals/:id';

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
