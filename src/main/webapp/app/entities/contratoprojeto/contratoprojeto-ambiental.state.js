(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('contratoprojeto-ambiental', {
            parent: 'entity',
            url: '/contratoprojeto-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.contratoprojeto.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/contratoprojeto/contratoprojetosambiental.html',
                    controller: 'ContratoprojetoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('contratoprojeto');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('contratoprojeto-ambiental-detail', {
            parent: 'contratoprojeto-ambiental',
            url: '/contratoprojeto-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.contratoprojeto.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/contratoprojeto/contratoprojeto-ambiental-detail.html',
                    controller: 'ContratoprojetoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('contratoprojeto');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Contratoprojeto', function($stateParams, Contratoprojeto) {
                    return Contratoprojeto.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'contratoprojeto-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('contratoprojeto-ambiental-detail.edit', {
            parent: 'contratoprojeto-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contratoprojeto/contratoprojeto-ambiental-dialog.html',
                    controller: 'ContratoprojetoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Contratoprojeto', function(Contratoprojeto) {
                            return Contratoprojeto.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('contratoprojeto-ambiental.new', {
            parent: 'contratoprojeto-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contratoprojeto/contratoprojeto-ambiental-dialog.html',
                    controller: 'ContratoprojetoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                tipo: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('contratoprojeto-ambiental', null, { reload: 'contratoprojeto-ambiental' });
                }, function() {
                    $state.go('contratoprojeto-ambiental');
                });
            }]
        })
        .state('contratoprojeto-ambiental.edit', {
            parent: 'contratoprojeto-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contratoprojeto/contratoprojeto-ambiental-dialog.html',
                    controller: 'ContratoprojetoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Contratoprojeto', function(Contratoprojeto) {
                            return Contratoprojeto.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('contratoprojeto-ambiental', null, { reload: 'contratoprojeto-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('contratoprojeto-ambiental.delete', {
            parent: 'contratoprojeto-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contratoprojeto/contratoprojeto-ambiental-delete-dialog.html',
                    controller: 'ContratoprojetoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Contratoprojeto', function(Contratoprojeto) {
                            return Contratoprojeto.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('contratoprojeto-ambiental', null, { reload: 'contratoprojeto-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
