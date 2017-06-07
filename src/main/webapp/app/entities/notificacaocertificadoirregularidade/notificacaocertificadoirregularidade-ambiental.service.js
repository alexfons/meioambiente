(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Notificacaocertificadoirregularidade', Notificacaocertificadoirregularidade);

    Notificacaocertificadoirregularidade.$inject = ['$resource'];

    function Notificacaocertificadoirregularidade ($resource) {
        var resourceUrl =  'api/notificacaocertificadoirregularidades/:id';

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
