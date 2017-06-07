(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('supre-ambiental', {
            parent: 'entity',
            url: '/supre-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.supre.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/supre/supresambiental.html',
                    controller: 'SupreAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('supre');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('supre-ambiental-detail', {
            parent: 'supre-ambiental',
            url: '/supre-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.supre.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/supre/supre-ambiental-detail.html',
                    controller: 'SupreAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('supre');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Supre', function($stateParams, Supre) {
                    return Supre.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'supre-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('supre-ambiental-detail.edit', {
            parent: 'supre-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/supre/supre-ambiental-dialog.html',
                    controller: 'SupreAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Supre', function(Supre) {
                            return Supre.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('supre-ambiental.new', {
            parent: 'supre-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/supre/supre-ambiental-dialog.html',
                    controller: 'SupreAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                cdorgaoset: null,
                                descricao: null,
                                nome: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('supre-ambiental', null, { reload: 'supre-ambiental' });
                }, function() {
                    $state.go('supre-ambiental');
                });
            }]
        })
        .state('supre-ambiental.edit', {
            parent: 'supre-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/supre/supre-ambiental-dialog.html',
                    controller: 'SupreAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Supre', function(Supre) {
                            return Supre.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('supre-ambiental', null, { reload: 'supre-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('supre-ambiental.delete', {
            parent: 'supre-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/supre/supre-ambiental-delete-dialog.html',
                    controller: 'SupreAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Supre', function(Supre) {
                            return Supre.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('supre-ambiental', null, { reload: 'supre-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
