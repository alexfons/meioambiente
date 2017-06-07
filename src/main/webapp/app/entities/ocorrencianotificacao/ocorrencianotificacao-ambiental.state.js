(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('ocorrencianotificacao-ambiental', {
            parent: 'entity',
            url: '/ocorrencianotificacao-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.ocorrencianotificacao.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ocorrencianotificacao/ocorrencianotificacaosambiental.html',
                    controller: 'OcorrencianotificacaoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ocorrencianotificacao');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('ocorrencianotificacao-ambiental-detail', {
            parent: 'ocorrencianotificacao-ambiental',
            url: '/ocorrencianotificacao-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.ocorrencianotificacao.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ocorrencianotificacao/ocorrencianotificacao-ambiental-detail.html',
                    controller: 'OcorrencianotificacaoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ocorrencianotificacao');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Ocorrencianotificacao', function($stateParams, Ocorrencianotificacao) {
                    return Ocorrencianotificacao.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'ocorrencianotificacao-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('ocorrencianotificacao-ambiental-detail.edit', {
            parent: 'ocorrencianotificacao-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrencianotificacao/ocorrencianotificacao-ambiental-dialog.html',
                    controller: 'OcorrencianotificacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Ocorrencianotificacao', function(Ocorrencianotificacao) {
                            return Ocorrencianotificacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ocorrencianotificacao-ambiental.new', {
            parent: 'ocorrencianotificacao-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrencianotificacao/ocorrencianotificacao-ambiental-dialog.html',
                    controller: 'OcorrencianotificacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                enquadramento: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('ocorrencianotificacao-ambiental', null, { reload: 'ocorrencianotificacao-ambiental' });
                }, function() {
                    $state.go('ocorrencianotificacao-ambiental');
                });
            }]
        })
        .state('ocorrencianotificacao-ambiental.edit', {
            parent: 'ocorrencianotificacao-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrencianotificacao/ocorrencianotificacao-ambiental-dialog.html',
                    controller: 'OcorrencianotificacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Ocorrencianotificacao', function(Ocorrencianotificacao) {
                            return Ocorrencianotificacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ocorrencianotificacao-ambiental', null, { reload: 'ocorrencianotificacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ocorrencianotificacao-ambiental.delete', {
            parent: 'ocorrencianotificacao-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrencianotificacao/ocorrencianotificacao-ambiental-delete-dialog.html',
                    controller: 'OcorrencianotificacaoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Ocorrencianotificacao', function(Ocorrencianotificacao) {
                            return Ocorrencianotificacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ocorrencianotificacao-ambiental', null, { reload: 'ocorrencianotificacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
