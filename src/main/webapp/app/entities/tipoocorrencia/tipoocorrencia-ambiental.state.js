(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tipoocorrencia-ambiental', {
            parent: 'entity',
            url: '/tipoocorrencia-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tipoocorrencia.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipoocorrencia/tipoocorrenciasambiental.html',
                    controller: 'TipoocorrenciaAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipoocorrencia');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('tipoocorrencia-ambiental-detail', {
            parent: 'tipoocorrencia-ambiental',
            url: '/tipoocorrencia-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tipoocorrencia.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipoocorrencia/tipoocorrencia-ambiental-detail.html',
                    controller: 'TipoocorrenciaAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipoocorrencia');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Tipoocorrencia', function($stateParams, Tipoocorrencia) {
                    return Tipoocorrencia.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'tipoocorrencia-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('tipoocorrencia-ambiental-detail.edit', {
            parent: 'tipoocorrencia-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipoocorrencia/tipoocorrencia-ambiental-dialog.html',
                    controller: 'TipoocorrenciaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tipoocorrencia', function(Tipoocorrencia) {
                            return Tipoocorrencia.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipoocorrencia-ambiental.new', {
            parent: 'tipoocorrencia-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipoocorrencia/tipoocorrencia-ambiental-dialog.html',
                    controller: 'TipoocorrenciaAmbientalDialogController',
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
                    $state.go('tipoocorrencia-ambiental', null, { reload: 'tipoocorrencia-ambiental' });
                }, function() {
                    $state.go('tipoocorrencia-ambiental');
                });
            }]
        })
        .state('tipoocorrencia-ambiental.edit', {
            parent: 'tipoocorrencia-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipoocorrencia/tipoocorrencia-ambiental-dialog.html',
                    controller: 'TipoocorrenciaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tipoocorrencia', function(Tipoocorrencia) {
                            return Tipoocorrencia.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipoocorrencia-ambiental', null, { reload: 'tipoocorrencia-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipoocorrencia-ambiental.delete', {
            parent: 'tipoocorrencia-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipoocorrencia/tipoocorrencia-ambiental-delete-dialog.html',
                    controller: 'TipoocorrenciaAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Tipoocorrencia', function(Tipoocorrencia) {
                            return Tipoocorrencia.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipoocorrencia-ambiental', null, { reload: 'tipoocorrencia-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
