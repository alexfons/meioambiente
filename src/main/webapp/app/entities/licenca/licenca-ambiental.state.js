(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('licenca-ambiental', {
            parent: 'entity',
            url: '/licenca-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.licenca.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/licenca/licencasambiental.html',
                    controller: 'LicencaAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('licenca');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('licenca-ambiental-detail', {
            parent: 'licenca-ambiental',
            url: '/licenca-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.licenca.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/licenca/licenca-ambiental-detail.html',
                    controller: 'LicencaAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('licenca');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Licenca', function($stateParams, Licenca) {
                    return Licenca.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'licenca-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('licenca-ambiental-detail.edit', {
            parent: 'licenca-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/licenca/licenca-ambiental-dialog.html',
                    controller: 'LicencaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Licenca', function(Licenca) {
                            return Licenca.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('licenca-ambiental.new', {
            parent: 'licenca-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/licenca/licenca-ambiental-dialog.html',
                    controller: 'LicencaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                album: null,
                                andamento: null,
                                compensacaoambiental: null,
                                dataemissao: null,
                                dataentregadocs: null,
                                dataexpedicaoprorrogacao1: null,
                                dataexpedicaoprorrogacao2: null,
                                dataexpedicaoprorrogacao3: null,
                                dataoficiolocalpedido: null,
                                dataoficiolocalrecebimento: null,
                                dataoficioreoficialpedido: null,
                                dataoficioreoficialrecebimento: null,
                                datapedidoprorrogacao1: null,
                                datapedidoprorrogacao2: null,
                                datapedidoprorrogacao3: null,
                                datavalidadeprorrogacao1: null,
                                datavalidadeprorrogacao2: null,
                                datavalidadeprorrogacao3: null,
                                datavencimento: null,
                                datavencimentoatual: null,
                                descricao: null,
                                dispensalai: null,
                                docsentregues: null,
                                eiarima: null,
                                fcei: null,
                                fceidatapagamento: null,
                                fceidataprotocolo: null,
                                fceivalor: null,
                                folder: null,
                                inativo: null,
                                inventarioflorestal: null,
                                numero: null,
                                numerolap: null,
                                numerooficioconcessaoprorrogacao1: null,
                                numerooficioconcessaoprorrogacao2: null,
                                numerooficioconcessaoprorrogacao3: null,
                                numerooficiolocalpedido: null,
                                numerooficiolocalrecebimento: null,
                                numerooficiooficialpedido: null,
                                numerooficiooficialrecebimento: null,
                                numerooficioprorrogacao1: null,
                                numerooficioprorrogacao2: null,
                                numerooficioprorrogacao3: null,
                                numeroparecertecnico: null,
                                numeroprocesso: null,
                                observacao: null,
                                pendente: null,
                                prazomes: null,
                                prazovalidade: null,
                                reciboentregadocs: null,
                                usosolo: null,
                                usosoloobs: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('licenca-ambiental', null, { reload: 'licenca-ambiental' });
                }, function() {
                    $state.go('licenca-ambiental');
                });
            }]
        })
        .state('licenca-ambiental.edit', {
            parent: 'licenca-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/licenca/licenca-ambiental-dialog.html',
                    controller: 'LicencaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Licenca', function(Licenca) {
                            return Licenca.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('licenca-ambiental', null, { reload: 'licenca-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('licenca-ambiental.delete', {
            parent: 'licenca-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/licenca/licenca-ambiental-delete-dialog.html',
                    controller: 'LicencaAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Licenca', function(Licenca) {
                            return Licenca.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('licenca-ambiental', null, { reload: 'licenca-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
