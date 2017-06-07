(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('registro-ambiental', {
            parent: 'entity',
            url: '/registro-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.registro.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/registro/registrosambiental.html',
                    controller: 'RegistroAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('registro');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('registro-ambiental-detail', {
            parent: 'registro-ambiental',
            url: '/registro-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.registro.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/registro/registro-ambiental-detail.html',
                    controller: 'RegistroAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('registro');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Registro', function($stateParams, Registro) {
                    return Registro.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'registro-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('registro-ambiental-detail.edit', {
            parent: 'registro-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/registro/registro-ambiental-dialog.html',
                    controller: 'RegistroAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Registro', function(Registro) {
                            return Registro.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('registro-ambiental.new', {
            parent: 'registro-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/registro/registro-ambiental-dialog.html',
                    controller: 'RegistroAmbientalDialogController',
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
                    $state.go('registro-ambiental', null, { reload: 'registro-ambiental' });
                }, function() {
                    $state.go('registro-ambiental');
                });
            }]
        })
        .state('registro-ambiental.edit', {
            parent: 'registro-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/registro/registro-ambiental-dialog.html',
                    controller: 'RegistroAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Registro', function(Registro) {
                            return Registro.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('registro-ambiental', null, { reload: 'registro-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('registro-ambiental.delete', {
            parent: 'registro-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/registro/registro-ambiental-delete-dialog.html',
                    controller: 'RegistroAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Registro', function(Registro) {
                            return Registro.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('registro-ambiental', null, { reload: 'registro-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
