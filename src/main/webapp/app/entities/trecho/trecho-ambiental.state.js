(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('trecho-ambiental', {
            parent: 'entity',
            url: '/trecho-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.trecho.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/trecho/trechosambiental.html',
                    controller: 'TrechoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('trecho');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('trecho-ambiental-detail', {
            parent: 'trecho-ambiental',
            url: '/trecho-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.trecho.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/trecho/trecho-ambiental-detail.html',
                    controller: 'TrechoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('trecho');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Trecho', function($stateParams, Trecho) {
                    return Trecho.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'trecho-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('trecho-ambiental-detail.edit', {
            parent: 'trecho-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/trecho/trecho-ambiental-dialog.html',
                    controller: 'TrechoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Trecho', function(Trecho) {
                            return Trecho.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('trecho-ambiental.new', {
            parent: 'trecho-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/trecho/trecho-ambiental-dialog.html',
                    controller: 'TrechoAmbientalDialogController',
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
                    $state.go('trecho-ambiental', null, { reload: 'trecho-ambiental' });
                }, function() {
                    $state.go('trecho-ambiental');
                });
            }]
        })
        .state('trecho-ambiental.edit', {
            parent: 'trecho-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/trecho/trecho-ambiental-dialog.html',
                    controller: 'TrechoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Trecho', function(Trecho) {
                            return Trecho.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('trecho-ambiental', null, { reload: 'trecho-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('trecho-ambiental.delete', {
            parent: 'trecho-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/trecho/trecho-ambiental-delete-dialog.html',
                    controller: 'TrechoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Trecho', function(Trecho) {
                            return Trecho.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('trecho-ambiental', null, { reload: 'trecho-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
