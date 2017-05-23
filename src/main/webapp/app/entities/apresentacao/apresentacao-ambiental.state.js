(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('apresentacao-ambiental', {
            parent: 'entity',
            url: '/apresentacao-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.apresentacao.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/apresentacao/apresentacaosambiental.html',
                    controller: 'ApresentacaoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('apresentacao');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('apresentacao-ambiental-detail', {
            parent: 'apresentacao-ambiental',
            url: '/apresentacao-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.apresentacao.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/apresentacao/apresentacao-ambiental-detail.html',
                    controller: 'ApresentacaoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('apresentacao');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Apresentacao', function($stateParams, Apresentacao) {
                    return Apresentacao.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'apresentacao-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('apresentacao-ambiental-detail.edit', {
            parent: 'apresentacao-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/apresentacao/apresentacao-ambiental-dialog.html',
                    controller: 'ApresentacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Apresentacao', function(Apresentacao) {
                            return Apresentacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('apresentacao-ambiental.new', {
            parent: 'apresentacao-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/apresentacao/apresentacao-ambiental-dialog.html',
                    controller: 'ApresentacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                numero: null,
                                obs: null,
                                notificacao: null,
                                data: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('apresentacao-ambiental', null, { reload: 'apresentacao-ambiental' });
                }, function() {
                    $state.go('apresentacao-ambiental');
                });
            }]
        })
        .state('apresentacao-ambiental.edit', {
            parent: 'apresentacao-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/apresentacao/apresentacao-ambiental-dialog.html',
                    controller: 'ApresentacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Apresentacao', function(Apresentacao) {
                            return Apresentacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('apresentacao-ambiental', null, { reload: 'apresentacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('apresentacao-ambiental.delete', {
            parent: 'apresentacao-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/apresentacao/apresentacao-ambiental-delete-dialog.html',
                    controller: 'ApresentacaoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Apresentacao', function(Apresentacao) {
                            return Apresentacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('apresentacao-ambiental', null, { reload: 'apresentacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
