(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('condicionante-ambiental', {
            parent: 'entity',
            url: '/condicionante-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.condicionante.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/condicionante/condicionantesambiental.html',
                    controller: 'CondicionanteAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('condicionante');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('condicionante-ambiental-detail', {
            parent: 'condicionante-ambiental',
            url: '/condicionante-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.condicionante.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/condicionante/condicionante-ambiental-detail.html',
                    controller: 'CondicionanteAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('condicionante');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Condicionante', function($stateParams, Condicionante) {
                    return Condicionante.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'condicionante-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('condicionante-ambiental-detail.edit', {
            parent: 'condicionante-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/condicionante/condicionante-ambiental-dialog.html',
                    controller: 'CondicionanteAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Condicionante', function(Condicionante) {
                            return Condicionante.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('condicionante-ambiental.new', {
            parent: 'condicionante-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/condicionante/condicionante-ambiental-dialog.html',
                    controller: 'CondicionanteAmbientalDialogController',
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
                    $state.go('condicionante-ambiental', null, { reload: 'condicionante-ambiental' });
                }, function() {
                    $state.go('condicionante-ambiental');
                });
            }]
        })
        .state('condicionante-ambiental.edit', {
            parent: 'condicionante-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/condicionante/condicionante-ambiental-dialog.html',
                    controller: 'CondicionanteAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Condicionante', function(Condicionante) {
                            return Condicionante.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('condicionante-ambiental', null, { reload: 'condicionante-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('condicionante-ambiental.delete', {
            parent: 'condicionante-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/condicionante/condicionante-ambiental-delete-dialog.html',
                    controller: 'CondicionanteAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Condicionante', function(Condicionante) {
                            return Condicionante.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('condicionante-ambiental', null, { reload: 'condicionante-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
