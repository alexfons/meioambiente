(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('contratoagente-ambiental', {
            parent: 'entity',
            url: '/contratoagente-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.contratoagente.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/contratoagente/contratoagentesambiental.html',
                    controller: 'ContratoagenteAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('contratoagente');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('contratoagente-ambiental-detail', {
            parent: 'contratoagente-ambiental',
            url: '/contratoagente-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.contratoagente.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/contratoagente/contratoagente-ambiental-detail.html',
                    controller: 'ContratoagenteAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('contratoagente');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Contratoagente', function($stateParams, Contratoagente) {
                    return Contratoagente.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'contratoagente-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('contratoagente-ambiental-detail.edit', {
            parent: 'contratoagente-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contratoagente/contratoagente-ambiental-dialog.html',
                    controller: 'ContratoagenteAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Contratoagente', function(Contratoagente) {
                            return Contratoagente.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('contratoagente-ambiental.new', {
            parent: 'contratoagente-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contratoagente/contratoagente-ambiental-dialog.html',
                    controller: 'ContratoagenteAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                dataaprovacao: null,
                                dataassinatura: null,
                                dataconclusao: null,
                                datainicio: null,
                                idcontratoagente: null,
                                nomecontratoagente: null,
                                referenciabid: null,
                                acordocredito: null,
                                mutuario: null,
                                executor: null,
                                clausulascontratuais: null,
                                descricao: null,
                                moeda: null,
                                percentuallocal: null,
                                percentualagente: null,
                                valorcontrato: null,
                                valor: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('contratoagente-ambiental', null, { reload: 'contratoagente-ambiental' });
                }, function() {
                    $state.go('contratoagente-ambiental');
                });
            }]
        })
        .state('contratoagente-ambiental.edit', {
            parent: 'contratoagente-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contratoagente/contratoagente-ambiental-dialog.html',
                    controller: 'ContratoagenteAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Contratoagente', function(Contratoagente) {
                            return Contratoagente.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('contratoagente-ambiental', null, { reload: 'contratoagente-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('contratoagente-ambiental.delete', {
            parent: 'contratoagente-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contratoagente/contratoagente-ambiental-delete-dialog.html',
                    controller: 'ContratoagenteAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Contratoagente', function(Contratoagente) {
                            return Contratoagente.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('contratoagente-ambiental', null, { reload: 'contratoagente-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
