(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('faturacontrato-ambiental', {
            parent: 'entity',
            url: '/faturacontrato-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.faturacontrato.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/faturacontrato/faturacontratoesambiental.html',
                    controller: 'FaturacontratoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('faturacontrato');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('faturacontrato-ambiental-detail', {
            parent: 'faturacontrato-ambiental',
            url: '/faturacontrato-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.faturacontrato.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/faturacontrato/faturacontrato-ambiental-detail.html',
                    controller: 'FaturacontratoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('faturacontrato');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Faturacontrato', function($stateParams, Faturacontrato) {
                    return Faturacontrato.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'faturacontrato-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('faturacontrato-ambiental-detail.edit', {
            parent: 'faturacontrato-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/faturacontrato/faturacontrato-ambiental-dialog.html',
                    controller: 'FaturacontratoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Faturacontrato', function(Faturacontrato) {
                            return Faturacontrato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('faturacontrato-ambiental.new', {
            parent: 'faturacontrato-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/faturacontrato/faturacontrato-ambiental-dialog.html',
                    controller: 'FaturacontratoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                dataop: null,
                                despesasexeanteriores: null,
                                idfaturacontrato: null,
                                nfatura: null,
                                nprocesso: null,
                                nsolicitacao: null,
                                parcela: null,
                                nobancaria: null,
                                nop: null,
                                nummedicao: null,
                                restosapagar: null,
                                tipomedicao: null,
                                valorpi: null,
                                valorreajuste: null,
                                valorus: null,
                                vreajuste: null,
                                aportelocal: null,
                                aporteagente: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('faturacontrato-ambiental', null, { reload: 'faturacontrato-ambiental' });
                }, function() {
                    $state.go('faturacontrato-ambiental');
                });
            }]
        })
        .state('faturacontrato-ambiental.edit', {
            parent: 'faturacontrato-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/faturacontrato/faturacontrato-ambiental-dialog.html',
                    controller: 'FaturacontratoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Faturacontrato', function(Faturacontrato) {
                            return Faturacontrato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('faturacontrato-ambiental', null, { reload: 'faturacontrato-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('faturacontrato-ambiental.delete', {
            parent: 'faturacontrato-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/faturacontrato/faturacontrato-ambiental-delete-dialog.html',
                    controller: 'FaturacontratoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Faturacontrato', function(Faturacontrato) {
                            return Faturacontrato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('faturacontrato-ambiental', null, { reload: 'faturacontrato-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
