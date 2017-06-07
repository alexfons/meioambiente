(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('documento-ambiental', {
            parent: 'entity',
            url: '/documento-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.documento.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/documento/documentosambiental.html',
                    controller: 'DocumentoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('documento');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('documento-ambiental-detail', {
            parent: 'documento-ambiental',
            url: '/documento-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.documento.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/documento/documento-ambiental-detail.html',
                    controller: 'DocumentoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('documento');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Documento', function($stateParams, Documento) {
                    return Documento.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'documento-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('documento-ambiental-detail.edit', {
            parent: 'documento-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/documento/documento-ambiental-dialog.html',
                    controller: 'DocumentoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Documento', function(Documento) {
                            return Documento.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('documento-ambiental.new', {
            parent: 'documento-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/documento/documento-ambiental-dialog.html',
                    controller: 'DocumentoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                data: null,
                                descricao: null,
                                documento: null,
                                link: null,
                                thumb: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('documento-ambiental', null, { reload: 'documento-ambiental' });
                }, function() {
                    $state.go('documento-ambiental');
                });
            }]
        })
        .state('documento-ambiental.edit', {
            parent: 'documento-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/documento/documento-ambiental-dialog.html',
                    controller: 'DocumentoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Documento', function(Documento) {
                            return Documento.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('documento-ambiental', null, { reload: 'documento-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('documento-ambiental.delete', {
            parent: 'documento-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/documento/documento-ambiental-delete-dialog.html',
                    controller: 'DocumentoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Documento', function(Documento) {
                            return Documento.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('documento-ambiental', null, { reload: 'documento-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
