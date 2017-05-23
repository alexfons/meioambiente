(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('auc-ambiental', {
            parent: 'entity',
            url: '/auc-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.auc.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/auc/aucsambiental.html',
                    controller: 'AucAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('auc');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('auc-ambiental-detail', {
            parent: 'auc-ambiental',
            url: '/auc-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.auc.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/auc/auc-ambiental-detail.html',
                    controller: 'AucAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('auc');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Auc', function($stateParams, Auc) {
                    return Auc.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'auc-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('auc-ambiental-detail.edit', {
            parent: 'auc-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/auc/auc-ambiental-dialog.html',
                    controller: 'AucAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Auc', function(Auc) {
                            return Auc.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('auc-ambiental.new', {
            parent: 'auc-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/auc/auc-ambiental-dialog.html',
                    controller: 'AucAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                fceivalor: null,
                                inativo: null,
                                prazomes: null,
                                reposicaoflorestal: null,
                                compensacaoambiental: null,
                                prazovalidade: null,
                                fcei: null,
                                reciboentregadocs: null,
                                numero: null,
                                numeroprocesso: null,
                                numerooficiolocalpedido: null,
                                numerooficiooficialpedido: null,
                                numerooficiolocalrecebimento: null,
                                numerooficiooficialrecebimento: null,
                                numeroparecertecnico: null,
                                numerooficioprorrogacao1: null,
                                numerooficioconcessaoprorrogacao1: null,
                                numerooficioprorrogacao2: null,
                                numerooficioconcessaoprorrogacao2: null,
                                numerooficioprorrogacao3: null,
                                numerooficioconcessaoprorrogacao3: null,
                                album: null,
                                folder: null,
                                pendente: null,
                                andamento: null,
                                descricao: null,
                                observacao: null,
                                docsentregues: null,
                                reposicaoflorestalobs: null,
                                compensacaoambientalobs: null,
                                fceidataprotocolo: null,
                                fceidatapagamento: null,
                                dataentregadocs: null,
                                dataoficiolocalpedido: null,
                                dataoficioreoficialpedido: null,
                                dataoficiolocalrecebimento: null,
                                dataoficioreoficialrecebimento: null,
                                dataemissao: null,
                                datapedidoprorrogacao1: null,
                                dataexpedicaoprorrogacao1: null,
                                datavalidadeprorrogacao1: null,
                                datapedidoprorrogacao2: null,
                                dataexpedicaoprorrogacao2: null,
                                datavalidadeprorrogacao2: null,
                                datapedidoprorrogacao3: null,
                                dataexpedicaoprorrogacao3: null,
                                datavalidadeprorrogacao3: null,
                                datavencimento: null,
                                datavencimentoatual: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('auc-ambiental', null, { reload: 'auc-ambiental' });
                }, function() {
                    $state.go('auc-ambiental');
                });
            }]
        })
        .state('auc-ambiental.edit', {
            parent: 'auc-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/auc/auc-ambiental-dialog.html',
                    controller: 'AucAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Auc', function(Auc) {
                            return Auc.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('auc-ambiental', null, { reload: 'auc-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('auc-ambiental.delete', {
            parent: 'auc-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/auc/auc-ambiental-delete-dialog.html',
                    controller: 'AucAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Auc', function(Auc) {
                            return Auc.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('auc-ambiental', null, { reload: 'auc-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
