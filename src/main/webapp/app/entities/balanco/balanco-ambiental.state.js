(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('balanco-ambiental', {
            parent: 'entity',
            url: '/balanco-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.balanco.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/balanco/balancosambiental.html',
                    controller: 'BalancoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('balanco');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('balanco-ambiental-detail', {
            parent: 'balanco-ambiental',
            url: '/balanco-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.balanco.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/balanco/balanco-ambiental-detail.html',
                    controller: 'BalancoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('balanco');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Balanco', function($stateParams, Balanco) {
                    return Balanco.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'balanco-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('balanco-ambiental-detail.edit', {
            parent: 'balanco-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/balanco/balanco-ambiental-dialog.html',
                    controller: 'BalancoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Balanco', function(Balanco) {
                            return Balanco.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('balanco-ambiental.new', {
            parent: 'balanco-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/balanco/balanco-ambiental-dialog.html',
                    controller: 'BalancoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                datafim: null,
                                datainicio: null,
                                descricao: null,
                                taxa: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('balanco-ambiental', null, { reload: 'balanco-ambiental' });
                }, function() {
                    $state.go('balanco-ambiental');
                });
            }]
        })
        .state('balanco-ambiental.edit', {
            parent: 'balanco-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/balanco/balanco-ambiental-dialog.html',
                    controller: 'BalancoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Balanco', function(Balanco) {
                            return Balanco.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('balanco-ambiental', null, { reload: 'balanco-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('balanco-ambiental.delete', {
            parent: 'balanco-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/balanco/balanco-ambiental-delete-dialog.html',
                    controller: 'BalancoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Balanco', function(Balanco) {
                            return Balanco.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('balanco-ambiental', null, { reload: 'balanco-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
