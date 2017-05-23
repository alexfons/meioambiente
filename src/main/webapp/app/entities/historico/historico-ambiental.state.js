(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('historico-ambiental', {
            parent: 'entity',
            url: '/historico-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.historico.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/historico/historicosambiental.html',
                    controller: 'HistoricoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('historico');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('historico-ambiental-detail', {
            parent: 'historico-ambiental',
            url: '/historico-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.historico.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/historico/historico-ambiental-detail.html',
                    controller: 'HistoricoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('historico');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Historico', function($stateParams, Historico) {
                    return Historico.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'historico-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('historico-ambiental-detail.edit', {
            parent: 'historico-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/historico/historico-ambiental-dialog.html',
                    controller: 'HistoricoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Historico', function(Historico) {
                            return Historico.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('historico-ambiental.new', {
            parent: 'historico-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/historico/historico-ambiental-dialog.html',
                    controller: 'HistoricoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('historico-ambiental', null, { reload: 'historico-ambiental' });
                }, function() {
                    $state.go('historico-ambiental');
                });
            }]
        })
        .state('historico-ambiental.edit', {
            parent: 'historico-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/historico/historico-ambiental-dialog.html',
                    controller: 'HistoricoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Historico', function(Historico) {
                            return Historico.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('historico-ambiental', null, { reload: 'historico-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('historico-ambiental.delete', {
            parent: 'historico-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/historico/historico-ambiental-delete-dialog.html',
                    controller: 'HistoricoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Historico', function(Historico) {
                            return Historico.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('historico-ambiental', null, { reload: 'historico-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
