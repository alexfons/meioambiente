(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('solicitacao-ambiental', {
            parent: 'entity',
            url: '/solicitacao-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.solicitacao.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/solicitacao/solicitacaosambiental.html',
                    controller: 'SolicitacaoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('solicitacao');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('solicitacao-ambiental-detail', {
            parent: 'solicitacao-ambiental',
            url: '/solicitacao-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.solicitacao.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/solicitacao/solicitacao-ambiental-detail.html',
                    controller: 'SolicitacaoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('solicitacao');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Solicitacao', function($stateParams, Solicitacao) {
                    return Solicitacao.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'solicitacao-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('solicitacao-ambiental-detail.edit', {
            parent: 'solicitacao-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/solicitacao/solicitacao-ambiental-dialog.html',
                    controller: 'SolicitacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Solicitacao', function(Solicitacao) {
                            return Solicitacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('solicitacao-ambiental.new', {
            parent: 'solicitacao-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/solicitacao/solicitacao-ambiental-dialog.html',
                    controller: 'SolicitacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                apresentamos: null,
                                data: null,
                                descricao: null,
                                extenso: null,
                                extenso1: null,
                                idsolicitacao: null,
                                moeda: null,
                                montante: null,
                                nsolicitacao: null,
                                oficio: null,
                                solicitamos: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('solicitacao-ambiental', null, { reload: 'solicitacao-ambiental' });
                }, function() {
                    $state.go('solicitacao-ambiental');
                });
            }]
        })
        .state('solicitacao-ambiental.edit', {
            parent: 'solicitacao-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/solicitacao/solicitacao-ambiental-dialog.html',
                    controller: 'SolicitacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Solicitacao', function(Solicitacao) {
                            return Solicitacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('solicitacao-ambiental', null, { reload: 'solicitacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('solicitacao-ambiental.delete', {
            parent: 'solicitacao-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/solicitacao/solicitacao-ambiental-delete-dialog.html',
                    controller: 'SolicitacaoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Solicitacao', function(Solicitacao) {
                            return Solicitacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('solicitacao-ambiental', null, { reload: 'solicitacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
