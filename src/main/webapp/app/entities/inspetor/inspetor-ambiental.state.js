(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('inspetor-ambiental', {
            parent: 'entity',
            url: '/inspetor-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.inspetor.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/inspetor/inspetorsambiental.html',
                    controller: 'InspetorAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('inspetor');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('inspetor-ambiental-detail', {
            parent: 'inspetor-ambiental',
            url: '/inspetor-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.inspetor.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/inspetor/inspetor-ambiental-detail.html',
                    controller: 'InspetorAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('inspetor');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Inspetor', function($stateParams, Inspetor) {
                    return Inspetor.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'inspetor-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('inspetor-ambiental-detail.edit', {
            parent: 'inspetor-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/inspetor/inspetor-ambiental-dialog.html',
                    controller: 'InspetorAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Inspetor', function(Inspetor) {
                            return Inspetor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('inspetor-ambiental.new', {
            parent: 'inspetor-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/inspetor/inspetor-ambiental-dialog.html',
                    controller: 'InspetorAmbientalDialogController',
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
                    $state.go('inspetor-ambiental', null, { reload: 'inspetor-ambiental' });
                }, function() {
                    $state.go('inspetor-ambiental');
                });
            }]
        })
        .state('inspetor-ambiental.edit', {
            parent: 'inspetor-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/inspetor/inspetor-ambiental-dialog.html',
                    controller: 'InspetorAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Inspetor', function(Inspetor) {
                            return Inspetor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('inspetor-ambiental', null, { reload: 'inspetor-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('inspetor-ambiental.delete', {
            parent: 'inspetor-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/inspetor/inspetor-ambiental-delete-dialog.html',
                    controller: 'InspetorAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Inspetor', function(Inspetor) {
                            return Inspetor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('inspetor-ambiental', null, { reload: 'inspetor-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
