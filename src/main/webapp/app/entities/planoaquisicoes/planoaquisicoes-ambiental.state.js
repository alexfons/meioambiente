(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('planoaquisicoes-ambiental', {
            parent: 'entity',
            url: '/planoaquisicoes-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.planoaquisicoes.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/planoaquisicoes/planoaquisicoesambiental.html',
                    controller: 'PlanoaquisicoesAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('planoaquisicoes');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('planoaquisicoes-ambiental-detail', {
            parent: 'planoaquisicoes-ambiental',
            url: '/planoaquisicoes-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.planoaquisicoes.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/planoaquisicoes/planoaquisicoes-ambiental-detail.html',
                    controller: 'PlanoaquisicoesAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('planoaquisicoes');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Planoaquisicoes', function($stateParams, Planoaquisicoes) {
                    return Planoaquisicoes.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'planoaquisicoes-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('planoaquisicoes-ambiental-detail.edit', {
            parent: 'planoaquisicoes-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/planoaquisicoes/planoaquisicoes-ambiental-dialog.html',
                    controller: 'PlanoaquisicoesAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Planoaquisicoes', function(Planoaquisicoes) {
                            return Planoaquisicoes.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('planoaquisicoes-ambiental.new', {
            parent: 'planoaquisicoes-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/planoaquisicoes/planoaquisicoes-ambiental-dialog.html',
                    controller: 'PlanoaquisicoesAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                avisolicitacao: null,
                                custoestimado: null,
                                aportelocal: null,
                                aporteagente: null,
                                descricao: null,
                                metodo: null,
                                revisao: null,
                                prequalificado: null,
                                situacao: null,
                                idplanoaquisicoes: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('planoaquisicoes-ambiental', null, { reload: 'planoaquisicoes-ambiental' });
                }, function() {
                    $state.go('planoaquisicoes-ambiental');
                });
            }]
        })
        .state('planoaquisicoes-ambiental.edit', {
            parent: 'planoaquisicoes-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/planoaquisicoes/planoaquisicoes-ambiental-dialog.html',
                    controller: 'PlanoaquisicoesAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Planoaquisicoes', function(Planoaquisicoes) {
                            return Planoaquisicoes.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('planoaquisicoes-ambiental', null, { reload: 'planoaquisicoes-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('planoaquisicoes-ambiental.delete', {
            parent: 'planoaquisicoes-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/planoaquisicoes/planoaquisicoes-ambiental-delete-dialog.html',
                    controller: 'PlanoaquisicoesAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Planoaquisicoes', function(Planoaquisicoes) {
                            return Planoaquisicoes.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('planoaquisicoes-ambiental', null, { reload: 'planoaquisicoes-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
