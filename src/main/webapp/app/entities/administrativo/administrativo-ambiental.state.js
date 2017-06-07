(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('administrativo-ambiental', {
            parent: 'entity',
            url: '/administrativo-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.administrativo.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/administrativo/administrativosambiental.html',
                    controller: 'AdministrativoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('administrativo');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('administrativo-ambiental-detail', {
            parent: 'administrativo-ambiental',
            url: '/administrativo-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.administrativo.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/administrativo/administrativo-ambiental-detail.html',
                    controller: 'AdministrativoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('administrativo');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Administrativo', function($stateParams, Administrativo) {
                    return Administrativo.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'administrativo-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('administrativo-ambiental-detail.edit', {
            parent: 'administrativo-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/administrativo/administrativo-ambiental-dialog.html',
                    controller: 'AdministrativoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Administrativo', function(Administrativo) {
                            return Administrativo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('administrativo-ambiental.new', {
            parent: 'administrativo-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/administrativo/administrativo-ambiental-dialog.html',
                    controller: 'AdministrativoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                album: null,
                                assunto: null,
                                consideracao: null,
                                data: null,
                                descricao: null,
                                folder: null,
                                local: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('administrativo-ambiental', null, { reload: 'administrativo-ambiental' });
                }, function() {
                    $state.go('administrativo-ambiental');
                });
            }]
        })
        .state('administrativo-ambiental.edit', {
            parent: 'administrativo-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/administrativo/administrativo-ambiental-dialog.html',
                    controller: 'AdministrativoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Administrativo', function(Administrativo) {
                            return Administrativo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('administrativo-ambiental', null, { reload: 'administrativo-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('administrativo-ambiental.delete', {
            parent: 'administrativo-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/administrativo/administrativo-ambiental-delete-dialog.html',
                    controller: 'AdministrativoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Administrativo', function(Administrativo) {
                            return Administrativo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('administrativo-ambiental', null, { reload: 'administrativo-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
