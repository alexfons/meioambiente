(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('desapropriacao-ambiental', {
            parent: 'entity',
            url: '/desapropriacao-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.desapropriacao.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/desapropriacao/desapropriacaosambiental.html',
                    controller: 'DesapropriacaoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('desapropriacao');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('desapropriacao-ambiental-detail', {
            parent: 'desapropriacao-ambiental',
            url: '/desapropriacao-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.desapropriacao.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/desapropriacao/desapropriacao-ambiental-detail.html',
                    controller: 'DesapropriacaoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('desapropriacao');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Desapropriacao', function($stateParams, Desapropriacao) {
                    return Desapropriacao.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'desapropriacao-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('desapropriacao-ambiental-detail.edit', {
            parent: 'desapropriacao-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/desapropriacao/desapropriacao-ambiental-dialog.html',
                    controller: 'DesapropriacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Desapropriacao', function(Desapropriacao) {
                            return Desapropriacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('desapropriacao-ambiental.new', {
            parent: 'desapropriacao-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/desapropriacao/desapropriacao-ambiental-dialog.html',
                    controller: 'DesapropriacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                dataop: null,
                                iddesapropria: null,
                                nprocesso: null,
                                nomedesapropriado: null,
                                local: null,
                                nsolicitacao: null,
                                valorpago: null,
                                valorus: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('desapropriacao-ambiental', null, { reload: 'desapropriacao-ambiental' });
                }, function() {
                    $state.go('desapropriacao-ambiental');
                });
            }]
        })
        .state('desapropriacao-ambiental.edit', {
            parent: 'desapropriacao-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/desapropriacao/desapropriacao-ambiental-dialog.html',
                    controller: 'DesapropriacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Desapropriacao', function(Desapropriacao) {
                            return Desapropriacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('desapropriacao-ambiental', null, { reload: 'desapropriacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('desapropriacao-ambiental.delete', {
            parent: 'desapropriacao-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/desapropriacao/desapropriacao-ambiental-delete-dialog.html',
                    controller: 'DesapropriacaoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Desapropriacao', function(Desapropriacao) {
                            return Desapropriacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('desapropriacao-ambiental', null, { reload: 'desapropriacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
