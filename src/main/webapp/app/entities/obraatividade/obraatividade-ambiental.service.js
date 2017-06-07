(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Obraatividade', Obraatividade);

    Obraatividade.$inject = ['$resource'];

    function Obraatividade ($resource) {
        var resourceUrl =  'api/obraatividades/:id';

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
