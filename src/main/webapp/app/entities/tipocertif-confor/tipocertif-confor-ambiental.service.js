(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('TipocertifConfor', TipocertifConfor);

    TipocertifConfor.$inject = ['$resource'];

    function TipocertifConfor ($resource) {
        var resourceUrl =  'api/tipocertif-confors/:id';

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
