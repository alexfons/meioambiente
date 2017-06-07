(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('notificacao-certif-irreg-ambiental', {
            parent: 'entity',
            url: '/notificacao-certif-irreg-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.notificacaoCertifIrreg.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/notificacao-certif-irreg/notificacao-certif-irregsambiental.html',
                    controller: 'NotificacaoCertifIrregAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('notificacaoCertifIrreg');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('notificacao-certif-irreg-ambiental-detail', {
            parent: 'notificacao-certif-irreg-ambiental',
            url: '/notificacao-certif-irreg-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.notificacaoCertifIrreg.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/notificacao-certif-irreg/notificacao-certif-irreg-ambiental-detail.html',
                    controller: 'NotificacaoCertifIrregAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('notificacaoCertifIrreg');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'NotificacaoCertifIrreg', function($stateParams, NotificacaoCertifIrreg) {
                    return NotificacaoCertifIrreg.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'notificacao-certif-irreg-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('notificacao-certif-irreg-ambiental-detail.edit', {
            parent: 'notificacao-certif-irreg-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/notificacao-certif-irreg/notificacao-certif-irreg-ambiental-dialog.html',
                    controller: 'NotificacaoCertifIrregAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['NotificacaoCertifIrreg', function(NotificacaoCertifIrreg) {
                            return NotificacaoCertifIrreg.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('notificacao-certif-irreg-ambiental.new', {
            parent: 'notificacao-certif-irreg-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/notificacao-certif-irreg/notificacao-certif-irreg-ambiental-dialog.html',
                    controller: 'NotificacaoCertifIrregAmbientalDialogController',
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
                    $state.go('notificacao-certif-irreg-ambiental', null, { reload: 'notificacao-certif-irreg-ambiental' });
                }, function() {
                    $state.go('notificacao-certif-irreg-ambiental');
                });
            }]
        })
        .state('notificacao-certif-irreg-ambiental.edit', {
            parent: 'notificacao-certif-irreg-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/notificacao-certif-irreg/notificacao-certif-irreg-ambiental-dialog.html',
                    controller: 'NotificacaoCertifIrregAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['NotificacaoCertifIrreg', function(NotificacaoCertifIrreg) {
                            return NotificacaoCertifIrreg.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('notificacao-certif-irreg-ambiental', null, { reload: 'notificacao-certif-irreg-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('notificacao-certif-irreg-ambiental.delete', {
            parent: 'notificacao-certif-irreg-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/notificacao-certif-irreg/notificacao-certif-irreg-ambiental-delete-dialog.html',
                    controller: 'NotificacaoCertifIrregAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['NotificacaoCertifIrreg', function(NotificacaoCertifIrreg) {
                            return NotificacaoCertifIrreg.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('notificacao-certif-irreg-ambiental', null, { reload: 'notificacao-certif-irreg-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
