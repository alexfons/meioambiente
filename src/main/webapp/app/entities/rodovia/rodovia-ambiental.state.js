(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('rodovia-ambiental', {
            parent: 'entity',
            url: '/rodovia-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.rodovia.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rodovia/rodoviasambiental.html',
                    controller: 'RodoviaAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('rodovia');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('rodovia-ambiental-detail', {
            parent: 'rodovia-ambiental',
            url: '/rodovia-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.rodovia.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rodovia/rodovia-ambiental-detail.html',
                    controller: 'RodoviaAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('rodovia');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Rodovia', function($stateParams, Rodovia) {
                    return Rodovia.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'rodovia-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('rodovia-ambiental-detail.edit', {
            parent: 'rodovia-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rodovia/rodovia-ambiental-dialog.html',
                    controller: 'RodoviaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Rodovia', function(Rodovia) {
                            return Rodovia.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rodovia-ambiental.new', {
            parent: 'rodovia-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rodovia/rodovia-ambiental-dialog.html',
                    controller: 'RodoviaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                sgrodovia: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('rodovia-ambiental', null, { reload: 'rodovia-ambiental' });
                }, function() {
                    $state.go('rodovia-ambiental');
                });
            }]
        })
        .state('rodovia-ambiental.edit', {
            parent: 'rodovia-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rodovia/rodovia-ambiental-dialog.html',
                    controller: 'RodoviaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Rodovia', function(Rodovia) {
                            return Rodovia.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rodovia-ambiental', null, { reload: 'rodovia-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rodovia-ambiental.delete', {
            parent: 'rodovia-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rodovia/rodovia-ambiental-delete-dialog.html',
                    controller: 'RodoviaAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Rodovia', function(Rodovia) {
                            return Rodovia.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rodovia-ambiental', null, { reload: 'rodovia-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
