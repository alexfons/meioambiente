(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('NotificacaoCertifIrreg', NotificacaoCertifIrreg);

    NotificacaoCertifIrreg.$inject = ['$resource'];

    function NotificacaoCertifIrreg ($resource) {
        var resourceUrl =  'api/notificacao-certif-irregs/:id';

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
