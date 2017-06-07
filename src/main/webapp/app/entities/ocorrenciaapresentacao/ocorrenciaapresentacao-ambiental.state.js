(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('ocorrenciaapresentacao-ambiental', {
            parent: 'entity',
            url: '/ocorrenciaapresentacao-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.ocorrenciaapresentacao.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ocorrenciaapresentacao/ocorrenciaapresentacaosambiental.html',
                    controller: 'OcorrenciaapresentacaoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ocorrenciaapresentacao');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('ocorrenciaapresentacao-ambiental-detail', {
            parent: 'ocorrenciaapresentacao-ambiental',
            url: '/ocorrenciaapresentacao-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.ocorrenciaapresentacao.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ocorrenciaapresentacao/ocorrenciaapresentacao-ambiental-detail.html',
                    controller: 'OcorrenciaapresentacaoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ocorrenciaapresentacao');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Ocorrenciaapresentacao', function($stateParams, Ocorrenciaapresentacao) {
                    return Ocorrenciaapresentacao.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'ocorrenciaapresentacao-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('ocorrenciaapresentacao-ambiental-detail.edit', {
            parent: 'ocorrenciaapresentacao-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrenciaapresentacao/ocorrenciaapresentacao-ambiental-dialog.html',
                    controller: 'OcorrenciaapresentacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Ocorrenciaapresentacao', function(Ocorrenciaapresentacao) {
                            return Ocorrenciaapresentacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ocorrenciaapresentacao-ambiental.new', {
            parent: 'ocorrenciaapresentacao-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrenciaapresentacao/ocorrenciaapresentacao-ambiental-dialog.html',
                    controller: 'OcorrenciaapresentacaoAmbientalDialogController',
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
                    $state.go('ocorrenciaapresentacao-ambiental', null, { reload: 'ocorrenciaapresentacao-ambiental' });
                }, function() {
                    $state.go('ocorrenciaapresentacao-ambiental');
                });
            }]
        })
        .state('ocorrenciaapresentacao-ambiental.edit', {
            parent: 'ocorrenciaapresentacao-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrenciaapresentacao/ocorrenciaapresentacao-ambiental-dialog.html',
                    controller: 'OcorrenciaapresentacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Ocorrenciaapresentacao', function(Ocorrenciaapresentacao) {
                            return Ocorrenciaapresentacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ocorrenciaapresentacao-ambiental', null, { reload: 'ocorrenciaapresentacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ocorrenciaapresentacao-ambiental.delete', {
            parent: 'ocorrenciaapresentacao-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrenciaapresentacao/ocorrenciaapresentacao-ambiental-delete-dialog.html',
                    controller: 'OcorrenciaapresentacaoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Ocorrenciaapresentacao', function(Ocorrenciaapresentacao) {
                            return Ocorrenciaapresentacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ocorrenciaapresentacao-ambiental', null, { reload: 'ocorrenciaapresentacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
