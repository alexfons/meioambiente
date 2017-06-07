(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('ocorrenciainforme-ambiental', {
            parent: 'entity',
            url: '/ocorrenciainforme-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.ocorrenciainforme.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ocorrenciainforme/ocorrenciainformesambiental.html',
                    controller: 'OcorrenciainformeAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ocorrenciainforme');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('ocorrenciainforme-ambiental-detail', {
            parent: 'ocorrenciainforme-ambiental',
            url: '/ocorrenciainforme-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.ocorrenciainforme.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ocorrenciainforme/ocorrenciainforme-ambiental-detail.html',
                    controller: 'OcorrenciainformeAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ocorrenciainforme');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Ocorrenciainforme', function($stateParams, Ocorrenciainforme) {
                    return Ocorrenciainforme.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'ocorrenciainforme-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('ocorrenciainforme-ambiental-detail.edit', {
            parent: 'ocorrenciainforme-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrenciainforme/ocorrenciainforme-ambiental-dialog.html',
                    controller: 'OcorrenciainformeAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Ocorrenciainforme', function(Ocorrenciainforme) {
                            return Ocorrenciainforme.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ocorrenciainforme-ambiental.new', {
            parent: 'ocorrenciainforme-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrenciainforme/ocorrenciainforme-ambiental-dialog.html',
                    controller: 'OcorrenciainformeAmbientalDialogController',
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
                    $state.go('ocorrenciainforme-ambiental', null, { reload: 'ocorrenciainforme-ambiental' });
                }, function() {
                    $state.go('ocorrenciainforme-ambiental');
                });
            }]
        })
        .state('ocorrenciainforme-ambiental.edit', {
            parent: 'ocorrenciainforme-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrenciainforme/ocorrenciainforme-ambiental-dialog.html',
                    controller: 'OcorrenciainformeAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Ocorrenciainforme', function(Ocorrenciainforme) {
                            return Ocorrenciainforme.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ocorrenciainforme-ambiental', null, { reload: 'ocorrenciainforme-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ocorrenciainforme-ambiental.delete', {
            parent: 'ocorrenciainforme-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrenciainforme/ocorrenciainforme-ambiental-delete-dialog.html',
                    controller: 'OcorrenciainformeAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Ocorrenciainforme', function(Ocorrenciainforme) {
                            return Ocorrenciainforme.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ocorrenciainforme-ambiental', null, { reload: 'ocorrenciainforme-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
