(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('fatura-ambiental', {
            parent: 'entity',
            url: '/fatura-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.fatura.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fatura/faturasambiental.html',
                    controller: 'FaturaAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('fatura');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('fatura-ambiental-detail', {
            parent: 'fatura-ambiental',
            url: '/fatura-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.fatura.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fatura/fatura-ambiental-detail.html',
                    controller: 'FaturaAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('fatura');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Fatura', function($stateParams, Fatura) {
                    return Fatura.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'fatura-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('fatura-ambiental-detail.edit', {
            parent: 'fatura-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fatura/fatura-ambiental-dialog.html',
                    controller: 'FaturaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Fatura', function(Fatura) {
                            return Fatura.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fatura-ambiental.new', {
            parent: 'fatura-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fatura/fatura-ambiental-dialog.html',
                    controller: 'FaturaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                ajustecambio: null,
                                dataop: null,
                                despesasexeanteriores: null,
                                nfatura: null,
                                nprocesso: null,
                                nsolicitacao: null,
                                parcela: null,
                                nob: null,
                                nop: null,
                                nummedicao: null,
                                restosapagar: null,
                                tipomedicao: null,
                                valorpi: null,
                                valorreajuste: null,
                                valorus: null,
                                vreajuste: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('fatura-ambiental', null, { reload: 'fatura-ambiental' });
                }, function() {
                    $state.go('fatura-ambiental');
                });
            }]
        })
        .state('fatura-ambiental.edit', {
            parent: 'fatura-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fatura/fatura-ambiental-dialog.html',
                    controller: 'FaturaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Fatura', function(Fatura) {
                            return Fatura.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fatura-ambiental', null, { reload: 'fatura-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fatura-ambiental.delete', {
            parent: 'fatura-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fatura/fatura-ambiental-delete-dialog.html',
                    controller: 'FaturaAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Fatura', function(Fatura) {
                            return Fatura.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fatura-ambiental', null, { reload: 'fatura-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
