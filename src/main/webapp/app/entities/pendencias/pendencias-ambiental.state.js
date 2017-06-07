(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('pendencias-ambiental', {
            parent: 'entity',
            url: '/pendencias-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.pendencias.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/pendencias/pendenciasambiental.html',
                    controller: 'PendenciasAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('pendencias');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('pendencias-ambiental-detail', {
            parent: 'pendencias-ambiental',
            url: '/pendencias-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.pendencias.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/pendencias/pendencias-ambiental-detail.html',
                    controller: 'PendenciasAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('pendencias');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Pendencias', function($stateParams, Pendencias) {
                    return Pendencias.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'pendencias-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('pendencias-ambiental-detail.edit', {
            parent: 'pendencias-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pendencias/pendencias-ambiental-dialog.html',
                    controller: 'PendenciasAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Pendencias', function(Pendencias) {
                            return Pendencias.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('pendencias-ambiental.new', {
            parent: 'pendencias-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pendencias/pendencias-ambiental-dialog.html',
                    controller: 'PendenciasAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                data: null,
                                datainspecao: null,
                                notificacao: null,
                                numero: null,
                                obs: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('pendencias-ambiental', null, { reload: 'pendencias-ambiental' });
                }, function() {
                    $state.go('pendencias-ambiental');
                });
            }]
        })
        .state('pendencias-ambiental.edit', {
            parent: 'pendencias-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pendencias/pendencias-ambiental-dialog.html',
                    controller: 'PendenciasAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Pendencias', function(Pendencias) {
                            return Pendencias.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('pendencias-ambiental', null, { reload: 'pendencias-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('pendencias-ambiental.delete', {
            parent: 'pendencias-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pendencias/pendencias-ambiental-delete-dialog.html',
                    controller: 'PendenciasAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Pendencias', function(Pendencias) {
                            return Pendencias.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('pendencias-ambiental', null, { reload: 'pendencias-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
