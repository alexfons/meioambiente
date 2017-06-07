(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('contrato-ambiental', {
            parent: 'entity',
            url: '/contrato-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.contrato.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/contrato/contratoesambiental.html',
                    controller: 'ContratoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('contrato');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('contrato-ambiental-detail', {
            parent: 'contrato-ambiental',
            url: '/contrato-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.contrato.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/contrato/contrato-ambiental-detail.html',
                    controller: 'ContratoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('contrato');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Contrato', function($stateParams, Contrato) {
                    return Contrato.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'contrato-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('contrato-ambiental-detail.edit', {
            parent: 'contrato-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contrato/contrato-ambiental-dialog.html',
                    controller: 'ContratoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Contrato', function(Contrato) {
                            return Contrato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('contrato-ambiental.new', {
            parent: 'contrato-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contrato/contrato-ambiental-dialog.html',
                    controller: 'ContratoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                codigobid: null,
                                dataatual: null,
                                databasecontrato: null,
                                datacontratacao: null,
                                dataterminocaucao: null,
                                extatualcontrato: null,
                                extinicialcontrato: null,
                                ncontrato: null,
                                ordemservico: null,
                                prazoatual: null,
                                prazoinicial: null,
                                rodoviacontrato: null,
                                saldocontratual: null,
                                situacao: null,
                                taxaatual: null,
                                taxaoriginal: null,
                                tipocontrato: null,
                                titulocontrato: null,
                                trechocontrato: null,
                                valoratual: null,
                                valorcaucao: null,
                                valorpi: null,
                                valorreajuste: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('contrato-ambiental', null, { reload: 'contrato-ambiental' });
                }, function() {
                    $state.go('contrato-ambiental');
                });
            }]
        })
        .state('contrato-ambiental.edit', {
            parent: 'contrato-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contrato/contrato-ambiental-dialog.html',
                    controller: 'ContratoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Contrato', function(Contrato) {
                            return Contrato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('contrato-ambiental', null, { reload: 'contrato-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('contrato-ambiental.delete', {
            parent: 'contrato-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contrato/contrato-ambiental-delete-dialog.html',
                    controller: 'ContratoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Contrato', function(Contrato) {
                            return Contrato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('contrato-ambiental', null, { reload: 'contrato-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
