(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tipo-obra-ambiental', {
            parent: 'entity',
            url: '/tipo-obra-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tipoObra.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipo-obra/tipo-obrasambiental.html',
                    controller: 'TipoObraAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipoObra');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('tipo-obra-ambiental-detail', {
            parent: 'tipo-obra-ambiental',
            url: '/tipo-obra-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tipoObra.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipo-obra/tipo-obra-ambiental-detail.html',
                    controller: 'TipoObraAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipoObra');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'TipoObra', function($stateParams, TipoObra) {
                    return TipoObra.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'tipo-obra-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('tipo-obra-ambiental-detail.edit', {
            parent: 'tipo-obra-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipo-obra/tipo-obra-ambiental-dialog.html',
                    controller: 'TipoObraAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['TipoObra', function(TipoObra) {
                            return TipoObra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipo-obra-ambiental.new', {
            parent: 'tipo-obra-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipo-obra/tipo-obra-ambiental-dialog.html',
                    controller: 'TipoObraAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                descricao: null,
                                categoria: null,
                                subcategoria: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('tipo-obra-ambiental', null, { reload: 'tipo-obra-ambiental' });
                }, function() {
                    $state.go('tipo-obra-ambiental');
                });
            }]
        })
        .state('tipo-obra-ambiental.edit', {
            parent: 'tipo-obra-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipo-obra/tipo-obra-ambiental-dialog.html',
                    controller: 'TipoObraAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['TipoObra', function(TipoObra) {
                            return TipoObra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipo-obra-ambiental', null, { reload: 'tipo-obra-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipo-obra-ambiental.delete', {
            parent: 'tipo-obra-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipo-obra/tipo-obra-ambiental-delete-dialog.html',
                    controller: 'TipoObraAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['TipoObra', function(TipoObra) {
                            return TipoObra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipo-obra-ambiental', null, { reload: 'tipo-obra-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
