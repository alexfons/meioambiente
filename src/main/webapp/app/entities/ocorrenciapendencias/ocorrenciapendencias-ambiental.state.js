(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('ocorrenciapendencias-ambiental', {
            parent: 'entity',
            url: '/ocorrenciapendencias-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.ocorrenciapendencias.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ocorrenciapendencias/ocorrenciapendenciasambiental.html',
                    controller: 'OcorrenciapendenciasAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ocorrenciapendencias');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('ocorrenciapendencias-ambiental-detail', {
            parent: 'ocorrenciapendencias-ambiental',
            url: '/ocorrenciapendencias-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.ocorrenciapendencias.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ocorrenciapendencias/ocorrenciapendencias-ambiental-detail.html',
                    controller: 'OcorrenciapendenciasAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ocorrenciapendencias');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Ocorrenciapendencias', function($stateParams, Ocorrenciapendencias) {
                    return Ocorrenciapendencias.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'ocorrenciapendencias-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('ocorrenciapendencias-ambiental-detail.edit', {
            parent: 'ocorrenciapendencias-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrenciapendencias/ocorrenciapendencias-ambiental-dialog.html',
                    controller: 'OcorrenciapendenciasAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Ocorrenciapendencias', function(Ocorrenciapendencias) {
                            return Ocorrenciapendencias.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ocorrenciapendencias-ambiental.new', {
            parent: 'ocorrenciapendencias-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrenciapendencias/ocorrenciapendencias-ambiental-dialog.html',
                    controller: 'OcorrenciapendenciasAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                enquadramento: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('ocorrenciapendencias-ambiental', null, { reload: 'ocorrenciapendencias-ambiental' });
                }, function() {
                    $state.go('ocorrenciapendencias-ambiental');
                });
            }]
        })
        .state('ocorrenciapendencias-ambiental.edit', {
            parent: 'ocorrenciapendencias-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrenciapendencias/ocorrenciapendencias-ambiental-dialog.html',
                    controller: 'OcorrenciapendenciasAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Ocorrenciapendencias', function(Ocorrenciapendencias) {
                            return Ocorrenciapendencias.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ocorrenciapendencias-ambiental', null, { reload: 'ocorrenciapendencias-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ocorrenciapendencias-ambiental.delete', {
            parent: 'ocorrenciapendencias-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrenciapendencias/ocorrenciapendencias-ambiental-delete-dialog.html',
                    controller: 'OcorrenciapendenciasAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Ocorrenciapendencias', function(Ocorrenciapendencias) {
                            return Ocorrenciapendencias.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ocorrenciapendencias-ambiental', null, { reload: 'ocorrenciapendencias-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
