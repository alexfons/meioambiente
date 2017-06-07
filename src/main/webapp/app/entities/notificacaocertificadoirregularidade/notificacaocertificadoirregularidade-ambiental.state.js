(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('notificacaocertificadoirregularidade-ambiental', {
            parent: 'entity',
            url: '/notificacaocertificadoirregularidade-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.notificacaocertificadoirregularidade.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/notificacaocertificadoirregularidade/notificacaocertificadoirregularidadesambiental.html',
                    controller: 'NotificacaocertificadoirregularidadeAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('notificacaocertificadoirregularidade');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('notificacaocertificadoirregularidade-ambiental-detail', {
            parent: 'notificacaocertificadoirregularidade-ambiental',
            url: '/notificacaocertificadoirregularidade-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.notificacaocertificadoirregularidade.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/notificacaocertificadoirregularidade/notificacaocertificadoirregularidade-ambiental-detail.html',
                    controller: 'NotificacaocertificadoirregularidadeAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('notificacaocertificadoirregularidade');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Notificacaocertificadoirregularidade', function($stateParams, Notificacaocertificadoirregularidade) {
                    return Notificacaocertificadoirregularidade.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'notificacaocertificadoirregularidade-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('notificacaocertificadoirregularidade-ambiental-detail.edit', {
            parent: 'notificacaocertificadoirregularidade-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/notificacaocertificadoirregularidade/notificacaocertificadoirregularidade-ambiental-dialog.html',
                    controller: 'NotificacaocertificadoirregularidadeAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Notificacaocertificadoirregularidade', function(Notificacaocertificadoirregularidade) {
                            return Notificacaocertificadoirregularidade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('notificacaocertificadoirregularidade-ambiental.new', {
            parent: 'notificacaocertificadoirregularidade-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/notificacaocertificadoirregularidade/notificacaocertificadoirregularidade-ambiental-dialog.html',
                    controller: 'NotificacaocertificadoirregularidadeAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('notificacaocertificadoirregularidade-ambiental', null, { reload: 'notificacaocertificadoirregularidade-ambiental' });
                }, function() {
                    $state.go('notificacaocertificadoirregularidade-ambiental');
                });
            }]
        })
        .state('notificacaocertificadoirregularidade-ambiental.edit', {
            parent: 'notificacaocertificadoirregularidade-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/notificacaocertificadoirregularidade/notificacaocertificadoirregularidade-ambiental-dialog.html',
                    controller: 'NotificacaocertificadoirregularidadeAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Notificacaocertificadoirregularidade', function(Notificacaocertificadoirregularidade) {
                            return Notificacaocertificadoirregularidade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('notificacaocertificadoirregularidade-ambiental', null, { reload: 'notificacaocertificadoirregularidade-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('notificacaocertificadoirregularidade-ambiental.delete', {
            parent: 'notificacaocertificadoirregularidade-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/notificacaocertificadoirregularidade/notificacaocertificadoirregularidade-ambiental-delete-dialog.html',
                    controller: 'NotificacaocertificadoirregularidadeAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Notificacaocertificadoirregularidade', function(Notificacaocertificadoirregularidade) {
                            return Notificacaocertificadoirregularidade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('notificacaocertificadoirregularidade-ambiental', null, { reload: 'notificacaocertificadoirregularidade-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
