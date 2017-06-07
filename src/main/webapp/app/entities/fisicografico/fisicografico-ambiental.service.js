(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Fisicografico', Fisicografico);

    Fisicografico.$inject = ['$resource'];

    function Fisicografico ($resource) {
        var resourceUrl =  'api/fisicograficos/:id';

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
