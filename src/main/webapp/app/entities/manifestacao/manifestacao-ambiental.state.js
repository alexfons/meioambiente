(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('manifestacao-ambiental', {
            parent: 'entity',
            url: '/manifestacao-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.manifestacao.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/manifestacao/manifestacaosambiental.html',
                    controller: 'ManifestacaoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('manifestacao');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('manifestacao-ambiental-detail', {
            parent: 'manifestacao-ambiental',
            url: '/manifestacao-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.manifestacao.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/manifestacao/manifestacao-ambiental-detail.html',
                    controller: 'ManifestacaoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('manifestacao');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Manifestacao', function($stateParams, Manifestacao) {
                    return Manifestacao.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'manifestacao-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('manifestacao-ambiental-detail.edit', {
            parent: 'manifestacao-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/manifestacao/manifestacao-ambiental-dialog.html',
                    controller: 'ManifestacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Manifestacao', function(Manifestacao) {
                            return Manifestacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('manifestacao-ambiental.new', {
            parent: 'manifestacao-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/manifestacao/manifestacao-ambiental-dialog.html',
                    controller: 'ManifestacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                dataaviso: null,
                                dataentrega: null,
                                numero: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('manifestacao-ambiental', null, { reload: 'manifestacao-ambiental' });
                }, function() {
                    $state.go('manifestacao-ambiental');
                });
            }]
        })
        .state('manifestacao-ambiental.edit', {
            parent: 'manifestacao-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/manifestacao/manifestacao-ambiental-dialog.html',
                    controller: 'ManifestacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Manifestacao', function(Manifestacao) {
                            return Manifestacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('manifestacao-ambiental', null, { reload: 'manifestacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('manifestacao-ambiental.delete', {
            parent: 'manifestacao-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/manifestacao/manifestacao-ambiental-delete-dialog.html',
                    controller: 'ManifestacaoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Manifestacao', function(Manifestacao) {
                            return Manifestacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('manifestacao-ambiental', null, { reload: 'manifestacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
