(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Atividadeexecutadamensal', Atividadeexecutadamensal);

    Atividadeexecutadamensal.$inject = ['$resource'];

    function Atividadeexecutadamensal ($resource) {
        var resourceUrl =  'api/atividadeexecutadamensals/:id';

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
