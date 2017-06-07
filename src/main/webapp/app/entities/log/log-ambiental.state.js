(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('log-ambiental', {
            parent: 'entity',
            url: '/log-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.log.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/log/logsambiental.html',
                    controller: 'LogAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('log');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('log-ambiental-detail', {
            parent: 'log-ambiental',
            url: '/log-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.log.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/log/log-ambiental-detail.html',
                    controller: 'LogAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('log');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Log', function($stateParams, Log) {
                    return Log.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'log-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('log-ambiental-detail.edit', {
            parent: 'log-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/log/log-ambiental-dialog.html',
                    controller: 'LogAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Log', function(Log) {
                            return Log.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('log-ambiental.new', {
            parent: 'log-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/log/log-ambiental-dialog.html',
                    controller: 'LogAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                data: null,
                                descricao: null,
                                metodo: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('log-ambiental', null, { reload: 'log-ambiental' });
                }, function() {
                    $state.go('log-ambiental');
                });
            }]
        })
        .state('log-ambiental.edit', {
            parent: 'log-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/log/log-ambiental-dialog.html',
                    controller: 'LogAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Log', function(Log) {
                            return Log.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('log-ambiental', null, { reload: 'log-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('log-ambiental.delete', {
            parent: 'log-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/log/log-ambiental-delete-dialog.html',
                    controller: 'LogAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Log', function(Log) {
                            return Log.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('log-ambiental', null, { reload: 'log-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
