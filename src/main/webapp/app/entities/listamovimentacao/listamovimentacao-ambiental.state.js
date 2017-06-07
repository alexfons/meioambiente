(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('listamovimentacao-ambiental', {
            parent: 'entity',
            url: '/listamovimentacao-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.listamovimentacao.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/listamovimentacao/listamovimentacaosambiental.html',
                    controller: 'ListamovimentacaoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('listamovimentacao');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('listamovimentacao-ambiental-detail', {
            parent: 'listamovimentacao-ambiental',
            url: '/listamovimentacao-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.listamovimentacao.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/listamovimentacao/listamovimentacao-ambiental-detail.html',
                    controller: 'ListamovimentacaoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('listamovimentacao');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Listamovimentacao', function($stateParams, Listamovimentacao) {
                    return Listamovimentacao.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'listamovimentacao-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('listamovimentacao-ambiental-detail.edit', {
            parent: 'listamovimentacao-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/listamovimentacao/listamovimentacao-ambiental-dialog.html',
                    controller: 'ListamovimentacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Listamovimentacao', function(Listamovimentacao) {
                            return Listamovimentacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('listamovimentacao-ambiental.new', {
            parent: 'listamovimentacao-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/listamovimentacao/listamovimentacao-ambiental-dialog.html',
                    controller: 'ListamovimentacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                tipolancamento: null,
                                historico: null,
                                valoruscredito: null,
                                valorrcredito: null,
                                valorusdebito: null,
                                valorrdebito: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('listamovimentacao-ambiental', null, { reload: 'listamovimentacao-ambiental' });
                }, function() {
                    $state.go('listamovimentacao-ambiental');
                });
            }]
        })
        .state('listamovimentacao-ambiental.edit', {
            parent: 'listamovimentacao-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/listamovimentacao/listamovimentacao-ambiental-dialog.html',
                    controller: 'ListamovimentacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Listamovimentacao', function(Listamovimentacao) {
                            return Listamovimentacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('listamovimentacao-ambiental', null, { reload: 'listamovimentacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('listamovimentacao-ambiental.delete', {
            parent: 'listamovimentacao-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/listamovimentacao/listamovimentacao-ambiental-delete-dialog.html',
                    controller: 'ListamovimentacaoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Listamovimentacao', function(Listamovimentacao) {
                            return Listamovimentacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('listamovimentacao-ambiental', null, { reload: 'listamovimentacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
