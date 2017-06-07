(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tipo-ambiental', {
            parent: 'entity',
            url: '/tipo-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tipo.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipo/tiposambiental.html',
                    controller: 'TipoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipo');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('tipo-ambiental-detail', {
            parent: 'tipo-ambiental',
            url: '/tipo-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tipo.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipo/tipo-ambiental-detail.html',
                    controller: 'TipoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipo');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Tipo', function($stateParams, Tipo) {
                    return Tipo.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'tipo-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('tipo-ambiental-detail.edit', {
            parent: 'tipo-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipo/tipo-ambiental-dialog.html',
                    controller: 'TipoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tipo', function(Tipo) {
                            return Tipo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipo-ambiental.new', {
            parent: 'tipo-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipo/tipo-ambiental-dialog.html',
                    controller: 'TipoAmbientalDialogController',
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
                    $state.go('tipo-ambiental', null, { reload: 'tipo-ambiental' });
                }, function() {
                    $state.go('tipo-ambiental');
                });
            }]
        })
        .state('tipo-ambiental.edit', {
            parent: 'tipo-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipo/tipo-ambiental-dialog.html',
                    controller: 'TipoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tipo', function(Tipo) {
                            return Tipo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipo-ambiental', null, { reload: 'tipo-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipo-ambiental.delete', {
            parent: 'tipo-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipo/tipo-ambiental-delete-dialog.html',
                    controller: 'TipoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Tipo', function(Tipo) {
                            return Tipo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipo-ambiental', null, { reload: 'tipo-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
