(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('residente-ambiental', {
            parent: 'entity',
            url: '/residente-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.residente.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/residente/residentesambiental.html',
                    controller: 'ResidenteAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('residente');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('residente-ambiental-detail', {
            parent: 'residente-ambiental',
            url: '/residente-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.residente.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/residente/residente-ambiental-detail.html',
                    controller: 'ResidenteAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('residente');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Residente', function($stateParams, Residente) {
                    return Residente.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'residente-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('residente-ambiental-detail.edit', {
            parent: 'residente-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/residente/residente-ambiental-dialog.html',
                    controller: 'ResidenteAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Residente', function(Residente) {
                            return Residente.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('residente-ambiental.new', {
            parent: 'residente-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/residente/residente-ambiental-dialog.html',
                    controller: 'ResidenteAmbientalDialogController',
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
                    $state.go('residente-ambiental', null, { reload: 'residente-ambiental' });
                }, function() {
                    $state.go('residente-ambiental');
                });
            }]
        })
        .state('residente-ambiental.edit', {
            parent: 'residente-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/residente/residente-ambiental-dialog.html',
                    controller: 'ResidenteAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Residente', function(Residente) {
                            return Residente.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('residente-ambiental', null, { reload: 'residente-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('residente-ambiental.delete', {
            parent: 'residente-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/residente/residente-ambiental-delete-dialog.html',
                    controller: 'ResidenteAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Residente', function(Residente) {
                            return Residente.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('residente-ambiental', null, { reload: 'residente-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
