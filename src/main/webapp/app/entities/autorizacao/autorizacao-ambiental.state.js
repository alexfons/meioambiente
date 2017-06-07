(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('autorizacao-ambiental', {
            parent: 'entity',
            url: '/autorizacao-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.autorizacao.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/autorizacao/autorizacaosambiental.html',
                    controller: 'AutorizacaoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('autorizacao');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('autorizacao-ambiental-detail', {
            parent: 'autorizacao-ambiental',
            url: '/autorizacao-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.autorizacao.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/autorizacao/autorizacao-ambiental-detail.html',
                    controller: 'AutorizacaoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('autorizacao');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Autorizacao', function($stateParams, Autorizacao) {
                    return Autorizacao.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'autorizacao-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('autorizacao-ambiental-detail.edit', {
            parent: 'autorizacao-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/autorizacao/autorizacao-ambiental-dialog.html',
                    controller: 'AutorizacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Autorizacao', function(Autorizacao) {
                            return Autorizacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('autorizacao-ambiental.new', {
            parent: 'autorizacao-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/autorizacao/autorizacao-ambiental-dialog.html',
                    controller: 'AutorizacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                album: null,
                                andamento: null,
                                data: null,
                                dataentregadocs: null,
                                dataexpedicaoprorrogacao1: null,
                                dataexpedicaoprorrogacao2: null,
                                dataexpedicaoprorrogacao3: null,
                                datapedidoprorrogacao1: null,
                                datapedidoprorrogacao2: null,
                                datapedidoprorrogacao3: null,
                                datavalidadeprorrogacao1: null,
                                datavalidadeprorrogacao2: null,
                                datavalidadeprorrogacao3: null,
                                datavencimento: null,
                                datavencimentoatual: null,
                                descricao: null,
                                docsentregues: null,
                                fcei: null,
                                fceidatapagamento: null,
                                fceidataprotocolo: null,
                                fceivalor: null,
                                folder: null,
                                inativo: null,
                                kmfim: null,
                                kminicio: null,
                                lado: null,
                                naoria: null,
                                numero: null,
                                numerooficioconcessaoprorrogacao1: null,
                                numerooficioconcessaoprorrogacao2: null,
                                numerooficioconcessaoprorrogacao3: null,
                                numerooficioprorrogacao1: null,
                                numerooficioprorrogacao2: null,
                                numerooficioprorrogacao3: null,
                                numeroprocesso: null,
                                observacao: null,
                                pendente: null,
                                prazomes: null,
                                prazovalidade: null,
                                proprietario: null,
                                reciboentregadocs: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('autorizacao-ambiental', null, { reload: 'autorizacao-ambiental' });
                }, function() {
                    $state.go('autorizacao-ambiental');
                });
            }]
        })
        .state('autorizacao-ambiental.edit', {
            parent: 'autorizacao-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/autorizacao/autorizacao-ambiental-dialog.html',
                    controller: 'AutorizacaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Autorizacao', function(Autorizacao) {
                            return Autorizacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('autorizacao-ambiental', null, { reload: 'autorizacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('autorizacao-ambiental.delete', {
            parent: 'autorizacao-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/autorizacao/autorizacao-ambiental-delete-dialog.html',
                    controller: 'AutorizacaoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Autorizacao', function(Autorizacao) {
                            return Autorizacao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('autorizacao-ambiental', null, { reload: 'autorizacao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
