(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('referencia-ambiental', {
            parent: 'entity',
            url: '/referencia-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.referencia.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/referencia/referenciasambiental.html',
                    controller: 'ReferenciaAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('referencia');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('referencia-ambiental-detail', {
            parent: 'referencia-ambiental',
            url: '/referencia-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.referencia.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/referencia/referencia-ambiental-detail.html',
                    controller: 'ReferenciaAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('referencia');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Referencia', function($stateParams, Referencia) {
                    return Referencia.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'referencia-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('referencia-ambiental-detail.edit', {
            parent: 'referencia-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/referencia/referencia-ambiental-dialog.html',
                    controller: 'ReferenciaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Referencia', function(Referencia) {
                            return Referencia.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('referencia-ambiental.new', {
            parent: 'referencia-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/referencia/referencia-ambiental-dialog.html',
                    controller: 'ReferenciaAmbientalDialogController',
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
                    $state.go('referencia-ambiental', null, { reload: 'referencia-ambiental' });
                }, function() {
                    $state.go('referencia-ambiental');
                });
            }]
        })
        .state('referencia-ambiental.edit', {
            parent: 'referencia-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/referencia/referencia-ambiental-dialog.html',
                    controller: 'ReferenciaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Referencia', function(Referencia) {
                            return Referencia.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('referencia-ambiental', null, { reload: 'referencia-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('referencia-ambiental.delete', {
            parent: 'referencia-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/referencia/referencia-ambiental-delete-dialog.html',
                    controller: 'ReferenciaAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Referencia', function(Referencia) {
                            return Referencia.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('referencia-ambiental', null, { reload: 'referencia-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
