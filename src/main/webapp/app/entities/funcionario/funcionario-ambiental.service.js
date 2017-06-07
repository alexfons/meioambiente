(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Funcionario', Funcionario);

    Funcionario.$inject = ['$resource'];

    function Funcionario ($resource) {
        var resourceUrl =  'api/funcionarios/:id';

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
