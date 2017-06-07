(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('contratoobra-ambiental', {
            parent: 'entity',
            url: '/contratoobra-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.contratoobra.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/contratoobra/contratoobrasambiental.html',
                    controller: 'ContratoobraAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('contratoobra');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('contratoobra-ambiental-detail', {
            parent: 'contratoobra-ambiental',
            url: '/contratoobra-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.contratoobra.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/contratoobra/contratoobra-ambiental-detail.html',
                    controller: 'ContratoobraAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('contratoobra');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Contratoobra', function($stateParams, Contratoobra) {
                    return Contratoobra.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'contratoobra-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('contratoobra-ambiental-detail.edit', {
            parent: 'contratoobra-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contratoobra/contratoobra-ambiental-dialog.html',
                    controller: 'ContratoobraAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Contratoobra', function(Contratoobra) {
                            return Contratoobra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('contratoobra-ambiental.new', {
            parent: 'contratoobra-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contratoobra/contratoobra-ambiental-dialog.html',
                    controller: 'ContratoobraAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                tipo: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('contratoobra-ambiental', null, { reload: 'contratoobra-ambiental' });
                }, function() {
                    $state.go('contratoobra-ambiental');
                });
            }]
        })
        .state('contratoobra-ambiental.edit', {
            parent: 'contratoobra-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contratoobra/contratoobra-ambiental-dialog.html',
                    controller: 'ContratoobraAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Contratoobra', function(Contratoobra) {
                            return Contratoobra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('contratoobra-ambiental', null, { reload: 'contratoobra-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('contratoobra-ambiental.delete', {
            parent: 'contratoobra-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contratoobra/contratoobra-ambiental-delete-dialog.html',
                    controller: 'ContratoobraAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Contratoobra', function(Contratoobra) {
                            return Contratoobra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('contratoobra-ambiental', null, { reload: 'contratoobra-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
