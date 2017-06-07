(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tipoobra-ambiental', {
            parent: 'entity',
            url: '/tipoobra-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tipoobra.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipoobra/tipoobrasambiental.html',
                    controller: 'TipoobraAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipoobra');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('tipoobra-ambiental-detail', {
            parent: 'tipoobra-ambiental',
            url: '/tipoobra-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tipoobra.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipoobra/tipoobra-ambiental-detail.html',
                    controller: 'TipoobraAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipoobra');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Tipoobra', function($stateParams, Tipoobra) {
                    return Tipoobra.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'tipoobra-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('tipoobra-ambiental-detail.edit', {
            parent: 'tipoobra-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipoobra/tipoobra-ambiental-dialog.html',
                    controller: 'TipoobraAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tipoobra', function(Tipoobra) {
                            return Tipoobra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipoobra-ambiental.new', {
            parent: 'tipoobra-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipoobra/tipoobra-ambiental-dialog.html',
                    controller: 'TipoobraAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                descricao: null,
                                categoria: null,
                                subcategoria: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('tipoobra-ambiental', null, { reload: 'tipoobra-ambiental' });
                }, function() {
                    $state.go('tipoobra-ambiental');
                });
            }]
        })
        .state('tipoobra-ambiental.edit', {
            parent: 'tipoobra-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipoobra/tipoobra-ambiental-dialog.html',
                    controller: 'TipoobraAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tipoobra', function(Tipoobra) {
                            return Tipoobra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipoobra-ambiental', null, { reload: 'tipoobra-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipoobra-ambiental.delete', {
            parent: 'tipoobra-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipoobra/tipoobra-ambiental-delete-dialog.html',
                    controller: 'TipoobraAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Tipoobra', function(Tipoobra) {
                            return Tipoobra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipoobra-ambiental', null, { reload: 'tipoobra-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
