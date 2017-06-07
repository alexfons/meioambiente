(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('coluna-ambiental', {
            parent: 'entity',
            url: '/coluna-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.coluna.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/coluna/colunasambiental.html',
                    controller: 'ColunaAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('coluna');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('coluna-ambiental-detail', {
            parent: 'coluna-ambiental',
            url: '/coluna-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.coluna.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/coluna/coluna-ambiental-detail.html',
                    controller: 'ColunaAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('coluna');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Coluna', function($stateParams, Coluna) {
                    return Coluna.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'coluna-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('coluna-ambiental-detail.edit', {
            parent: 'coluna-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/coluna/coluna-ambiental-dialog.html',
                    controller: 'ColunaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Coluna', function(Coluna) {
                            return Coluna.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('coluna-ambiental.new', {
            parent: 'coluna-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/coluna/coluna-ambiental-dialog.html',
                    controller: 'ColunaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                descricao: null,
                                lista: null,
                                sequencia: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('coluna-ambiental', null, { reload: 'coluna-ambiental' });
                }, function() {
                    $state.go('coluna-ambiental');
                });
            }]
        })
        .state('coluna-ambiental.edit', {
            parent: 'coluna-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/coluna/coluna-ambiental-dialog.html',
                    controller: 'ColunaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Coluna', function(Coluna) {
                            return Coluna.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('coluna-ambiental', null, { reload: 'coluna-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('coluna-ambiental.delete', {
            parent: 'coluna-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/coluna/coluna-ambiental-delete-dialog.html',
                    controller: 'ColunaAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Coluna', function(Coluna) {
                            return Coluna.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('coluna-ambiental', null, { reload: 'coluna-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
