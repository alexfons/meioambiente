(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('fonte-ambiental', {
            parent: 'entity',
            url: '/fonte-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.fonte.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fonte/fontesambiental.html',
                    controller: 'FonteAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('fonte');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('fonte-ambiental-detail', {
            parent: 'fonte-ambiental',
            url: '/fonte-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.fonte.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fonte/fonte-ambiental-detail.html',
                    controller: 'FonteAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('fonte');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Fonte', function($stateParams, Fonte) {
                    return Fonte.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'fonte-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('fonte-ambiental-detail.edit', {
            parent: 'fonte-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fonte/fonte-ambiental-dialog.html',
                    controller: 'FonteAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Fonte', function(Fonte) {
                            return Fonte.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fonte-ambiental.new', {
            parent: 'fonte-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fonte/fonte-ambiental-dialog.html',
                    controller: 'FonteAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                descricao: null,
                                fonte: null,
                                indiceagente: null,
                                indicelocal: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('fonte-ambiental', null, { reload: 'fonte-ambiental' });
                }, function() {
                    $state.go('fonte-ambiental');
                });
            }]
        })
        .state('fonte-ambiental.edit', {
            parent: 'fonte-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fonte/fonte-ambiental-dialog.html',
                    controller: 'FonteAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Fonte', function(Fonte) {
                            return Fonte.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fonte-ambiental', null, { reload: 'fonte-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fonte-ambiental.delete', {
            parent: 'fonte-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fonte/fonte-ambiental-delete-dialog.html',
                    controller: 'FonteAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Fonte', function(Fonte) {
                            return Fonte.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fonte-ambiental', null, { reload: 'fonte-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
