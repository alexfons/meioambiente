(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('linha-ambiental', {
            parent: 'entity',
            url: '/linha-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.linha.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/linha/linhasambiental.html',
                    controller: 'LinhaAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('linha');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('linha-ambiental-detail', {
            parent: 'linha-ambiental',
            url: '/linha-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.linha.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/linha/linha-ambiental-detail.html',
                    controller: 'LinhaAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('linha');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Linha', function($stateParams, Linha) {
                    return Linha.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'linha-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('linha-ambiental-detail.edit', {
            parent: 'linha-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/linha/linha-ambiental-dialog.html',
                    controller: 'LinhaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Linha', function(Linha) {
                            return Linha.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('linha-ambiental.new', {
            parent: 'linha-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/linha/linha-ambiental-dialog.html',
                    controller: 'LinhaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                sequencia: null,
                                valor: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('linha-ambiental', null, { reload: 'linha-ambiental' });
                }, function() {
                    $state.go('linha-ambiental');
                });
            }]
        })
        .state('linha-ambiental.edit', {
            parent: 'linha-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/linha/linha-ambiental-dialog.html',
                    controller: 'LinhaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Linha', function(Linha) {
                            return Linha.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('linha-ambiental', null, { reload: 'linha-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('linha-ambiental.delete', {
            parent: 'linha-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/linha/linha-ambiental-delete-dialog.html',
                    controller: 'LinhaAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Linha', function(Linha) {
                            return Linha.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('linha-ambiental', null, { reload: 'linha-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
