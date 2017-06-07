(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('coordenada-ambiental', {
            parent: 'entity',
            url: '/coordenada-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.coordenada.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/coordenada/coordenadasambiental.html',
                    controller: 'CoordenadaAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('coordenada');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('coordenada-ambiental-detail', {
            parent: 'coordenada-ambiental',
            url: '/coordenada-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.coordenada.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/coordenada/coordenada-ambiental-detail.html',
                    controller: 'CoordenadaAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('coordenada');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Coordenada', function($stateParams, Coordenada) {
                    return Coordenada.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'coordenada-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('coordenada-ambiental-detail.edit', {
            parent: 'coordenada-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/coordenada/coordenada-ambiental-dialog.html',
                    controller: 'CoordenadaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Coordenada', function(Coordenada) {
                            return Coordenada.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('coordenada-ambiental.new', {
            parent: 'coordenada-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/coordenada/coordenada-ambiental-dialog.html',
                    controller: 'CoordenadaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                a: null,
                                km: null,
                                n: null,
                                s: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('coordenada-ambiental', null, { reload: 'coordenada-ambiental' });
                }, function() {
                    $state.go('coordenada-ambiental');
                });
            }]
        })
        .state('coordenada-ambiental.edit', {
            parent: 'coordenada-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/coordenada/coordenada-ambiental-dialog.html',
                    controller: 'CoordenadaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Coordenada', function(Coordenada) {
                            return Coordenada.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('coordenada-ambiental', null, { reload: 'coordenada-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('coordenada-ambiental.delete', {
            parent: 'coordenada-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/coordenada/coordenada-ambiental-delete-dialog.html',
                    controller: 'CoordenadaAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Coordenada', function(Coordenada) {
                            return Coordenada.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('coordenada-ambiental', null, { reload: 'coordenada-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
