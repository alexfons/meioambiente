(function() {
    'use strict';
    angular
        .module('meioambienteApp')
        .factory('Script', Script);

    Script.$inject = ['$resource', 'DateUtils'];

    function Script ($resource, DateUtils) {
        var resourceUrl =  'api/scripts/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dataVerificacaoLicencas = DateUtils.convertDateTimeFromServer(data.dataVerificacaoLicencas);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
