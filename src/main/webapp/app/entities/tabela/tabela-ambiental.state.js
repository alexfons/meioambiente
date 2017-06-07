(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tabela-ambiental', {
            parent: 'entity',
            url: '/tabela-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tabela.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tabela/tabelasambiental.html',
                    controller: 'TabelaAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tabela');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('tabela-ambiental-detail', {
            parent: 'tabela-ambiental',
            url: '/tabela-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tabela.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tabela/tabela-ambiental-detail.html',
                    controller: 'TabelaAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tabela');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Tabela', function($stateParams, Tabela) {
                    return Tabela.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'tabela-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('tabela-ambiental-detail.edit', {
            parent: 'tabela-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tabela/tabela-ambiental-dialog.html',
                    controller: 'TabelaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tabela', function(Tabela) {
                            return Tabela.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tabela-ambiental.new', {
            parent: 'tabela-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tabela/tabela-ambiental-dialog.html',
                    controller: 'TabelaAmbientalDialogController',
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
                    $state.go('tabela-ambiental', null, { reload: 'tabela-ambiental' });
                }, function() {
                    $state.go('tabela-ambiental');
                });
            }]
        })
        .state('tabela-ambiental.edit', {
            parent: 'tabela-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tabela/tabela-ambiental-dialog.html',
                    controller: 'TabelaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tabela', function(Tabela) {
                            return Tabela.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tabela-ambiental', null, { reload: 'tabela-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tabela-ambiental.delete', {
            parent: 'tabela-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tabela/tabela-ambiental-delete-dialog.html',
                    controller: 'TabelaAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Tabela', function(Tabela) {
                            return Tabela.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tabela-ambiental', null, { reload: 'tabela-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
