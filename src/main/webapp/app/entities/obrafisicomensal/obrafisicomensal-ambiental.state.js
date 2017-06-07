(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('obrafisicomensal-ambiental', {
            parent: 'entity',
            url: '/obrafisicomensal-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.obrafisicomensal.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/obrafisicomensal/obrafisicomensalsambiental.html',
                    controller: 'ObrafisicomensalAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('obrafisicomensal');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('obrafisicomensal-ambiental-detail', {
            parent: 'obrafisicomensal-ambiental',
            url: '/obrafisicomensal-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.obrafisicomensal.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/obrafisicomensal/obrafisicomensal-ambiental-detail.html',
                    controller: 'ObrafisicomensalAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('obrafisicomensal');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Obrafisicomensal', function($stateParams, Obrafisicomensal) {
                    return Obrafisicomensal.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'obrafisicomensal-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('obrafisicomensal-ambiental-detail.edit', {
            parent: 'obrafisicomensal-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/obrafisicomensal/obrafisicomensal-ambiental-dialog.html',
                    controller: 'ObrafisicomensalAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Obrafisicomensal', function(Obrafisicomensal) {
                            return Obrafisicomensal.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('obrafisicomensal-ambiental.new', {
            parent: 'obrafisicomensal-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/obrafisicomensal/obrafisicomensal-ambiental-dialog.html',
                    controller: 'ObrafisicomensalAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                comentario: null,
                                datainspecao: null,
                                datarelatorio: null,
                                pessoal: null,
                                equipamento: null,
                                instalacaoapoio: null,
                                ritmo: null,
                                apresentacao: null,
                                qualidadeservicos: null,
                                cronograma: null,
                                prazodecorrido: null,
                                avancofisicoOAE: null,
                                avancofisicoponderado: null,
                                previsaoatual: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('obrafisicomensal-ambiental', null, { reload: 'obrafisicomensal-ambiental' });
                }, function() {
                    $state.go('obrafisicomensal-ambiental');
                });
            }]
        })
        .state('obrafisicomensal-ambiental.edit', {
            parent: 'obrafisicomensal-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/obrafisicomensal/obrafisicomensal-ambiental-dialog.html',
                    controller: 'ObrafisicomensalAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Obrafisicomensal', function(Obrafisicomensal) {
                            return Obrafisicomensal.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('obrafisicomensal-ambiental', null, { reload: 'obrafisicomensal-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('obrafisicomensal-ambiental.delete', {
            parent: 'obrafisicomensal-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/obrafisicomensal/obrafisicomensal-ambiental-delete-dialog.html',
                    controller: 'ObrafisicomensalAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Obrafisicomensal', function(Obrafisicomensal) {
                            return Obrafisicomensal.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('obrafisicomensal-ambiental', null, { reload: 'obrafisicomensal-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
