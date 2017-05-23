(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('obra-ambiental', {
            parent: 'entity',
            url: '/obra-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.obra.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/obra/obrasambiental.html',
                    controller: 'ObraAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('obra');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('obra-ambiental-detail', {
            parent: 'obra-ambiental',
            url: '/obra-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.obra.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/obra/obra-ambiental-detail.html',
                    controller: 'ObraAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('obra');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Obra', function($stateParams, Obra) {
                    return Obra.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'obra-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('obra-ambiental-detail.edit', {
            parent: 'obra-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/obra/obra-ambiental-dialog.html',
                    controller: 'ObraAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Obra', function(Obra) {
                            return Obra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('obra-ambiental.new', {
            parent: 'obra-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/obra/obra-ambiental-dialog.html',
                    controller: 'ObraAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                pendente: null,
                                map: null,
                                user: null,
                                track: null,
                                certificadoMes: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('obra-ambiental', null, { reload: 'obra-ambiental' });
                }, function() {
                    $state.go('obra-ambiental');
                });
            }]
        })
        .state('obra-ambiental.edit', {
            parent: 'obra-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/obra/obra-ambiental-dialog.html',
                    controller: 'ObraAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Obra', function(Obra) {
                            return Obra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('obra-ambiental', null, { reload: 'obra-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('obra-ambiental.delete', {
            parent: 'obra-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/obra/obra-ambiental-delete-dialog.html',
                    controller: 'ObraAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Obra', function(Obra) {
                            return Obra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('obra-ambiental', null, { reload: 'obra-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
