(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('municipio-ambiental', {
            parent: 'entity',
            url: '/municipio-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.municipio.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/municipio/municipiosambiental.html',
                    controller: 'MunicipioAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('municipio');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('municipio-ambiental-detail', {
            parent: 'municipio-ambiental',
            url: '/municipio-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.municipio.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/municipio/municipio-ambiental-detail.html',
                    controller: 'MunicipioAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('municipio');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Municipio', function($stateParams, Municipio) {
                    return Municipio.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'municipio-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('municipio-ambiental-detail.edit', {
            parent: 'municipio-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/municipio/municipio-ambiental-dialog.html',
                    controller: 'MunicipioAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Municipio', function(Municipio) {
                            return Municipio.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('municipio-ambiental.new', {
            parent: 'municipio-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/municipio/municipio-ambiental-dialog.html',
                    controller: 'MunicipioAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                descricao: null,
                                estado: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('municipio-ambiental', null, { reload: 'municipio-ambiental' });
                }, function() {
                    $state.go('municipio-ambiental');
                });
            }]
        })
        .state('municipio-ambiental.edit', {
            parent: 'municipio-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/municipio/municipio-ambiental-dialog.html',
                    controller: 'MunicipioAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Municipio', function(Municipio) {
                            return Municipio.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('municipio-ambiental', null, { reload: 'municipio-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('municipio-ambiental.delete', {
            parent: 'municipio-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/municipio/municipio-ambiental-delete-dialog.html',
                    controller: 'MunicipioAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Municipio', function(Municipio) {
                            return Municipio.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('municipio-ambiental', null, { reload: 'municipio-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
