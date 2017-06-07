(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tipoautorizacao-ambiental', {
            parent: 'entity',
            url: '/tipoautorizacao-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tipoautorizacao.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipoautorizacao/tipoautorizacaosambiental.html',
                    controller: 'TipoautorizacaoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipoautorizacao');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('tipoautorizacao-ambiental-detail', {
            parent: 'tipoautorizacao-ambiental',
            url: '/tipoautorizacao-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tipoautorizacao.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipoautorizacao/tipoautorizacao-ambiental-detail.html',
                    controller: 'TipoautorizacaoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipoautorizacao');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Tipoautorizacao', function($stateParams, Tipoautorizacao) {
                    return Tipoautorizacao.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'tipoautorizacao-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('tipoautorizacao-ambiental-detail.edit', {
            parent: 'tipoautorizacao-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipoautorizacao/tipoautorizacao-ambiental-dialog.html',
                    controller: 'TipoautorizacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tipoautorizacao', function(Tipoautorizacao) {
                            return Tipoautorizacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipoautorizacao-ambiental.new', {
            parent: 'tipoautorizacao-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipoautorizacao/tipoautorizacao-ambiental-dialog.html',
                    controller: 'TipoautorizacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                categoria: null,
                                descricao: null,
                                subcategoria: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('tipoautorizacao-ambiental', null, { reload: 'tipoautorizacao-ambiental' });
                }, function() {
                    $state.go('tipoautorizacao-ambiental');
                });
            }]
        })
        .state('tipoautorizacao-ambiental.edit', {
            parent: 'tipoautorizacao-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipoautorizacao/tipoautorizacao-ambiental-dialog.html',
                    controller: 'TipoautorizacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tipoautorizacao', function(Tipoautorizacao) {
                            return Tipoautorizacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipoautorizacao-ambiental', null, { reload: 'tipoautorizacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipoautorizacao-ambiental.delete', {
            parent: 'tipoautorizacao-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipoautorizacao/tipoautorizacao-ambiental-delete-dialog.html',
                    controller: 'TipoautorizacaoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Tipoautorizacao', function(Tipoautorizacao) {
                            return Tipoautorizacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipoautorizacao-ambiental', null, { reload: 'tipoautorizacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
