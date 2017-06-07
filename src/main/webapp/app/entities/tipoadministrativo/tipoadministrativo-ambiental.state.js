(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tipoadministrativo-ambiental', {
            parent: 'entity',
            url: '/tipoadministrativo-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tipoadministrativo.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipoadministrativo/tipoadministrativosambiental.html',
                    controller: 'TipoadministrativoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipoadministrativo');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('tipoadministrativo-ambiental-detail', {
            parent: 'tipoadministrativo-ambiental',
            url: '/tipoadministrativo-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tipoadministrativo.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipoadministrativo/tipoadministrativo-ambiental-detail.html',
                    controller: 'TipoadministrativoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipoadministrativo');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Tipoadministrativo', function($stateParams, Tipoadministrativo) {
                    return Tipoadministrativo.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'tipoadministrativo-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('tipoadministrativo-ambiental-detail.edit', {
            parent: 'tipoadministrativo-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipoadministrativo/tipoadministrativo-ambiental-dialog.html',
                    controller: 'TipoadministrativoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tipoadministrativo', function(Tipoadministrativo) {
                            return Tipoadministrativo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipoadministrativo-ambiental.new', {
            parent: 'tipoadministrativo-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipoadministrativo/tipoadministrativo-ambiental-dialog.html',
                    controller: 'TipoadministrativoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                categoria: null,
                                descricao: null,
                                subcategoria: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('tipoadministrativo-ambiental', null, { reload: 'tipoadministrativo-ambiental' });
                }, function() {
                    $state.go('tipoadministrativo-ambiental');
                });
            }]
        })
        .state('tipoadministrativo-ambiental.edit', {
            parent: 'tipoadministrativo-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipoadministrativo/tipoadministrativo-ambiental-dialog.html',
                    controller: 'TipoadministrativoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tipoadministrativo', function(Tipoadministrativo) {
                            return Tipoadministrativo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipoadministrativo-ambiental', null, { reload: 'tipoadministrativo-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipoadministrativo-ambiental.delete', {
            parent: 'tipoadministrativo-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipoadministrativo/tipoadministrativo-ambiental-delete-dialog.html',
                    controller: 'TipoadministrativoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Tipoadministrativo', function(Tipoadministrativo) {
                            return Tipoadministrativo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipoadministrativo-ambiental', null, { reload: 'tipoadministrativo-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
