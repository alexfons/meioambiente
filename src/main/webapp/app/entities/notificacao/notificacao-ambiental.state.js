(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('notificacao-ambiental', {
            parent: 'entity',
            url: '/notificacao-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.notificacao.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/notificacao/notificacaosambiental.html',
                    controller: 'NotificacaoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('notificacao');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('notificacao-ambiental-detail', {
            parent: 'notificacao-ambiental',
            url: '/notificacao-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.notificacao.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/notificacao/notificacao-ambiental-detail.html',
                    controller: 'NotificacaoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('notificacao');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Notificacao', function($stateParams, Notificacao) {
                    return Notificacao.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'notificacao-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('notificacao-ambiental-detail.edit', {
            parent: 'notificacao-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/notificacao/notificacao-ambiental-dialog.html',
                    controller: 'NotificacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Notificacao', function(Notificacao) {
                            return Notificacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('notificacao-ambiental.new', {
            parent: 'notificacao-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/notificacao/notificacao-ambiental-dialog.html',
                    controller: 'NotificacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                data: null,
                                datainspecao: null,
                                numero: null,
                                obs: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('notificacao-ambiental', null, { reload: 'notificacao-ambiental' });
                }, function() {
                    $state.go('notificacao-ambiental');
                });
            }]
        })
        .state('notificacao-ambiental.edit', {
            parent: 'notificacao-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/notificacao/notificacao-ambiental-dialog.html',
                    controller: 'NotificacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Notificacao', function(Notificacao) {
                            return Notificacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('notificacao-ambiental', null, { reload: 'notificacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('notificacao-ambiental.delete', {
            parent: 'notificacao-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/notificacao/notificacao-ambiental-delete-dialog.html',
                    controller: 'NotificacaoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Notificacao', function(Notificacao) {
                            return Notificacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('notificacao-ambiental', null, { reload: 'notificacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
