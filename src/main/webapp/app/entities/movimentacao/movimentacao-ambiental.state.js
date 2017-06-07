(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('movimentacao-ambiental', {
            parent: 'entity',
            url: '/movimentacao-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.movimentacao.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/movimentacao/movimentacaosambiental.html',
                    controller: 'MovimentacaoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('movimentacao');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('movimentacao-ambiental-detail', {
            parent: 'movimentacao-ambiental',
            url: '/movimentacao-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.movimentacao.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/movimentacao/movimentacao-ambiental-detail.html',
                    controller: 'MovimentacaoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('movimentacao');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Movimentacao', function($stateParams, Movimentacao) {
                    return Movimentacao.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'movimentacao-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('movimentacao-ambiental-detail.edit', {
            parent: 'movimentacao-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/movimentacao/movimentacao-ambiental-dialog.html',
                    controller: 'MovimentacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Movimentacao', function(Movimentacao) {
                            return Movimentacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('movimentacao-ambiental.new', {
            parent: 'movimentacao-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/movimentacao/movimentacao-ambiental-dialog.html',
                    controller: 'MovimentacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                data: null,
                                tipomovimentacao: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('movimentacao-ambiental', null, { reload: 'movimentacao-ambiental' });
                }, function() {
                    $state.go('movimentacao-ambiental');
                });
            }]
        })
        .state('movimentacao-ambiental.edit', {
            parent: 'movimentacao-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/movimentacao/movimentacao-ambiental-dialog.html',
                    controller: 'MovimentacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Movimentacao', function(Movimentacao) {
                            return Movimentacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('movimentacao-ambiental', null, { reload: 'movimentacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('movimentacao-ambiental.delete', {
            parent: 'movimentacao-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/movimentacao/movimentacao-ambiental-delete-dialog.html',
                    controller: 'MovimentacaoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Movimentacao', function(Movimentacao) {
                            return Movimentacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('movimentacao-ambiental', null, { reload: 'movimentacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
